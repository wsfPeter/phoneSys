package com.cienet.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cienet.bean.Message;
import com.cienet.bean.Pager;
import com.cienet.entity.Admin;
import com.cienet.entity.Role;
import com.cienet.service.AdminService;
import com.cienet.service.PhoneNoService;
import com.cienet.service.RoleService;
import com.cienet.util.JsonUtil;

@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PhoneNoService phoneNoService;

	/**
	 * 添加用户跳转
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		List<Role> list = roleService.getAll();
		model.addAttribute("allRoleList", list);
		return "admin_add";
	}

	@RequestMapping("/expired")
	public String expired() {
		return "expired";
	}

	@InitBinder("admin")
	public void binderAdmin(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("admin.");
	}

	/**
	 * 是否已存在 ajax验证
	 * 
	 * @param admin
	 * @return
	 */
	@RequestMapping("/checkUsername")
	@ResponseBody
	public Boolean checkUsername(Admin admin, String oldValue) {
		if (StringUtils.isNotEmpty(oldValue) && oldValue.equals(admin.getUsername())) {
			return true;
		}
		if (adminService.isExist("username", admin.getUsername())) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 远程用户登录。获得用户登录信息.
	 * 
	 * @param username
	 *            用户名
	 * @param pwd
	 *            密码
	 * @return 用户ID，登录状态，用户角色 以JSON的格式返回。
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/userLogin")
	@ResponseBody
	public String userLogin(String username, String pwd) throws UnsupportedEncodingException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
			Admin admin = null;
			if (StringUtils.isNotEmpty(username) && StringUtils.isNotBlank(pwd)) {
				admin = adminService.loginInfo(username, pwd);
			}
			if (admin != null) {
				map.put("id", admin.getId());
				Object[] obj = (Object[]) phoneNoService.getResourceStatista(admin.getId()).get(0);
				String roleStr = "";
				for (Role role : admin.getRoleSet()) {
					roleStr = role.getName();
				}
				map.put("role", roleStr);
				map.put("allPhoneNo", obj[0]);
				map.put("doneDownload", obj[1]);
				map.put("statu", "0");
			} else {
				map.put("statu", "1");
			}
		} catch (Exception e) {
			map.put("statu", "2");
		}
		return URLEncoder.encode(JsonUtil.toString(map), "utf-8");
	}

	/**
	 * 删除用户
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Message delete(String[] ids, HttpServletRequest request) {
		Message msg = new Message();
		try {
			adminService.deleteAdmin(ids);
			msg.setType("success");
			msg.setContent("操作成功");
		} catch (Exception e) {
			msg.setType("error");
			msg.setContent("操作失败,用户有了手机号!");
		}
		return msg;
	}

	@RequestMapping("/edit")
	public String edit(String id, Model model) {
		Admin admin = adminService.get(id);
		// 获得所有的角色
		List<Role> list = roleService.getAll();
		model.addAttribute("allRoleList", list);
		model.addAttribute("admin", admin);
		model.addAttribute("id", id);

		return "admin_edit";
	}

	@RequestMapping("/list")
	public String list(Pager pager, Model model, Admin admin) {

		DetachedCriteria criteria = DetachedCriteria.forClass(Admin.class);
		if (StringUtils.isNotEmpty(admin.getUsername())) {
			criteria.add(Restrictions.like("username", "%" + admin.getUsername() + "%"));
		}
		if (StringUtils.isNotEmpty(admin.getEmail())) {
			criteria.add(Restrictions.like("email", "%" + admin.getEmail() + "%"));
		}
		if (StringUtils.isNotEmpty(admin.getName())) {
			criteria.add(Restrictions.like("name", "%" + admin.getName() + "%"));
		}
		pager = adminService.findByPager(pager, criteria);
		model.addAttribute("pager", pager);
		model.addAttribute("admin", admin);
		return "admin_list";
	}

	@RequestMapping("/login")
	public String login(HttpSession session, Model model) throws Exception {
		Exception springSecurityLastException = (Exception) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		if (springSecurityLastException != null) {
			if (springSecurityLastException instanceof BadCredentialsException) {
				model.addAttribute("error", "您的用户名或密码错误");
			} else if (springSecurityLastException instanceof DisabledException) {
				model.addAttribute("error", "您的账号已被禁用,无法登录");
			} else if (springSecurityLastException instanceof SessionAuthenticationException) {
				model.addAttribute("error", "该账户已登录，无法重复登陆");
			} else {
				model.addAttribute("error", "出现未知错误,无法登录");
			}
			session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		}
		return "login";
	}

	@RequestMapping("/main")
	public String main(HttpServletRequest request, Model model) {
		String page = "";
		Admin admin = adminService.loadLoginAdmin();
		for (Role role : admin.getRoleSet()) {
			if ("管理员".equals(role.getName())) {
				page = "admin/list";
			} else if ("维护员".equals(role.getName())) {
				page = "phoneNo/list";
			} else if ("业务员".equals(role.getName())) {
				page = "adminPhoneNo/detail";
			}
		}
		model.addAttribute("page", page);
		return "main";
	}

	/**
	 * 添加新用户
	 */
	@RequestMapping("/save")
	public String save(@ModelAttribute Admin admin, String[] roleIds, Model model, HttpServletRequest request) {
		Set<Role> roleSet = new LinkedHashSet<Role>();
		Role r = null;
		for (String id : roleIds) {
			r = new Role();
			r.setId(id);
			roleSet.add(r);
		}

		String admin_id = admin.getId();

		admin.setId(admin_id);
		admin.setUsername(admin.getUsername());
		admin.setRoleSet(roleSet);
		String passwordMd5 = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
		admin.setPassword(passwordMd5);
		adminService.save(admin);

		model.addAttribute("url", "/admin/list");
		return SUCCESS;
	}

	/**
	 * 更新用户信息
	 */
	@RequestMapping("/update")
	public String update(Admin admin, String[] roleIds, Model model, HttpServletRequest request) {
		Admin adm = adminService.get(admin.getId());

		Set<Role> roleSet = new LinkedHashSet<Role>();
		for (String id : roleIds) {
			Role r = new Role();
			r.setId(id);
			roleSet.add(r);
		}

		BeanUtils.copyProperties(admin, adm, new String[] { "id", "password", "username", "createDate" });
		adm.setRoleSet(roleSet);
		if (StringUtils.isNotBlank(admin.getPassword())) {
			String passwordMd5 = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
			adm.setPassword(passwordMd5);
		}
		adminService.update(adm);

		model.addAttribute("url", "/admin/list");
		return SUCCESS;
	}
}
