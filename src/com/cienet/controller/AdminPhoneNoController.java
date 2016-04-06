package com.cienet.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cienet.bean.Pager;
import com.cienet.entity.Admin;
import com.cienet.entity.PhoneNo;
import com.cienet.service.AdminService;
import com.cienet.service.PhoneNoService;
import com.cienet.util.Constants;
import com.cienet.util.ExcelUtil;
import com.cienet.util.JsonUtil;

@Controller
@RequestMapping(value = "/adminPhoneNo")
public class AdminPhoneNoController extends BaseController {
	
	private static final Logger log = Logger.getLogger(AdminPhoneNoController.class);

	@Autowired
	private AdminService adminService;
	@Autowired
	private PhoneNoService phoneNoService;

	@InitBinder("adminPhoneNo")
	public void binderPhoneNo(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("adminPhoneNo.");
	}

	@RequestMapping("/fenPeiPhoneNo")
	public String fenPeiPhoneNo(Model model, String adminId, HttpServletRequest request) {

		// 获得所有业务员
		List<Object> operates = adminService.getUserOPerate();

		model.addAttribute("aid", adminId);
		model.addAttribute("admins", operates);
		return "phone_fenpei";
	}

	@RequestMapping("getUserPhoneNo")
	public void getUserPhoneNo(String id, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		// 获得未被分配的手机号的个数
		int noFenPeiCount = phoneNoService.getAllByStatu();
		Object[] phoneNoCount = new Object[1];
		Object[] doneDonwloadCount = new Object[1];

		if (StringUtils.isNotBlank(id)) {
			Object[] obj = (Object[]) phoneNoService.getResourceStatista(id).get(0);
			phoneNoCount[0] = obj[0];
			doneDonwloadCount[0] = obj[1];
		}
		List<Object> allData = new ArrayList<Object>();
		allData.add(noFenPeiCount);
		allData.add(phoneNoCount);
		allData.add(doneDonwloadCount);
		response.getWriter().print(JsonUtil.toString(allData));
	}

	@RequestMapping("phoneNoCountFenPei")
	public void getPhoneNoCountFenPei(String id, String limit, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		boolean flag = false;
		Admin a = null;
		PhoneNo p = null;
		Object[] phoneNos = phoneNoService.getLimitPhoneNo(Integer.parseInt(limit));

		if (phoneNos != null && phoneNos.length > 0) {
			a = adminService.load(id);
			// 加入用户的手机号
			for (Object phoneId : phoneNos) {

				p = phoneNoService.load((String) phoneId);
				// 分配后，手机号的分配状态,及导出状态
				p.setPhoneNoStatu("1");
				p.setStatu("0");
				// 设置手机号所属业务员
				p.setAdmin(a);
				phoneNoService.update(p);
			}
			flag = true;
		}
		response.getWriter().print(flag);
	}

	@RequestMapping("/detail")
	public String detail(Pager pager, Model model, String id, String phoneNo) {
		if (StringUtils.isEmpty(id)) {
			Admin admin = adminService.loadLoginAdmin();
			id = admin.getId();
		} else {
			model.addAttribute("del", "del");
		}
		DetachedCriteria criteria = DetachedCriteria.forClass(PhoneNo.class, "p");
		criteria.createAlias("admin", "a");
		criteria.add(Restrictions.eq("a.id", id));
		if (StringUtils.isNotEmpty(phoneNo)) {
			criteria.add(Restrictions.like("p.phoneNo", "%" + phoneNo + "%"));
		}

		pager = phoneNoService.findByPager(pager, criteria);

		model.addAttribute("pager", pager);
		model.addAttribute("id", id);
		model.addAttribute("phoneNo", phoneNo);
		return "admin_detail";
	}

	/**
	 * 远程用户获取手机号数据.
	 * 
	 * @param adminId
	 *            用户ID
	 * @param limit
	 *            个数
	 * @return 机主名，手机号 以JSON的格式返回。
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/userPhoneNo")
	@ResponseBody
	public String userLogin(String adminId, int limit) throws UnsupportedEncodingException {
		List<Object> phones = null;
		if (StringUtils.isNotEmpty(adminId)) {
			if (limit == 0) {
				limit = 5;
			}
			phones = phoneNoService.getUserPhoneNo(adminId, limit);
		} else {
			phones = new ArrayList<Object>();
		}
		return URLEncoder.encode(JsonUtil.toString(phones), "utf-8");
	}

	/**
	 * 手机号导入页面
	 */
	@RequestMapping("/toImport")
	public String toImport() {
		return "phone_import";
	}

	/**
	 * 导入手机号
	 */
	@RequestMapping("/saveImport")
	public String saveImport(Model model, HttpServletRequest request, MultipartFile mubileFile) throws Exception {
		// 成功次数
		int successCount = 0;
		// 失败次数
		int failCount = 0;
		Workbook workbook;
		// 解析主数据
		try {
			workbook = new XSSFWorkbook(mubileFile.getInputStream());
		} catch (Exception ex) {
			workbook = new HSSFWorkbook(mubileFile.getInputStream());
		}
		Sheet sheet = workbook.getSheetAt(0);
		if (sheet != null) {
			Row row = null;
			PhoneNo pn = null;
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				row = sheet.getRow(rowNum);
				if(null == row){
					log.info("此行没有数据.");
					continue;
				}
				pn = new PhoneNo();

				// 设置手机号
				String phoneNo = ExcelUtil.getCellStringValue(row, 0);
				if(StringUtils.isNotEmpty(phoneNo)){
					if (!phoneNo.matches("^1\\d{10}")) {
						log.info(phoneNo+"格式不正确.");
						failCount++;
						continue;
					} else if (phoneNoService.isExist("phoneNo", phoneNo)) {
						log.info(phoneNo+"已经存在."); 
						failCount++;
						continue;
					} else {
						pn.setPhoneNo(phoneNo);
					} 
				}else{
					continue;
				}

				// 设置手机姓名
				String phoneName = ExcelUtil.getCellStringValue(row, 1);
				if (StringUtils.isNotEmpty(phoneName)) {
					pn.setPhoneName(phoneName);
				}
				phoneNoService.save(pn);
				successCount++;
			}
		} else {
			log.info("导入文件内容为空");
			setErrorInfo("导入文件内容为空！");
		}

		model.addAttribute("url", "/phoneNo/list");
		setErrorInfo("共" + (successCount + failCount) + "手机号，" + successCount + "道导入成功，" + failCount + "道导入失败！");
		return SUCCESS;
	}

	/**
	 * 文件下载
	 */
	@RequestMapping("download")
	public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		request.setCharacterEncoding("UTF-8");

		String path = request.getSession().getServletContext().getRealPath("/") + Constants.DOWNLOAD_PATH + Constants.MUBILE_FILE_NAME;

		try {
			File file = new File(path);
			response.setContentType("application/x-excel");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + Constants.MUBILE_FILE_NAME);
			response.setHeader("Content-Length", String.valueOf(file.length()));
			in = new BufferedInputStream(new FileInputStream(file));
			out = new BufferedOutputStream(response.getOutputStream());
			byte[] data = new byte[1024];
			int len = 0;
			while (-1 != (len = in.read(data, 0, data.length))) {
				out.write(data, 0, len);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

}
