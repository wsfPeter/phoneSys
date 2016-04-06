package com.cienet.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cienet.entity.Admin;
import com.cienet.service.AdminService;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController extends BaseController {
    //private Logger log = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private AdminService adminService;

    @InitBinder("admin")
    public void binderAdmin(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("admin.");
    }

    @RequestMapping("/checkCurrentPassword")
    @ResponseBody
    public Boolean checkUsername(String currentPassword) {
        Admin admin = adminService.loadLoginAdmin();
        String currentPasswordMd5 = DigestUtils.md5DigestAsHex(currentPassword
                .getBytes());

        if (currentPasswordMd5.endsWith(admin.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/edit")
    public String edit(Model model) {
        Admin admin = adminService.loadLoginAdmin();
        model.addAttribute("admin", admin);
        //model.addAttribute("organizationList", organizationService.getAll());
        return "profile_edit";
    }

    /**
     * 更新用户信息
     */
    @RequestMapping("/update")
    public String update(Admin admin, String organizationId, Model model,
            HttpServletRequest request) {
        Admin persistent = adminService.loadLoginAdmin();
        // 部门设置
       /* if (StringUtils.isEmpty(organizationId)) {
        	persistent.setOrganization(null);
        } else {
        	persistent.setOrganization(organizationService.get(organizationId));
        }*/
        persistent.setName(admin.getName());
        if (StringUtils.isNotBlank(admin.getPassword())) {
            String passwordMd5 = DigestUtils.md5DigestAsHex(admin.getPassword()
                    .getBytes());
            persistent.setPassword(passwordMd5);
        }
        
        persistent.setEmail(admin.getEmail());
      // persistent.setMobile(admin.getMobile());
        
        adminService.update(persistent);
//        isRecordLog = true;
//        operationName = "账号设置";
//        logInfo = "账号设置：" + persistent.getUsername();
//        setLog(request);
        model.addAttribute("url", "/profile/edit");
        return SUCCESS;
    }
}
