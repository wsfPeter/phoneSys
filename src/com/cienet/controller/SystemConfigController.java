package com.cienet.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cienet.bean.SystemConfig;
import com.cienet.util.SystemConfigUtil;

@Controller
@RequestMapping(value = "/systemConfig")
public class SystemConfigController extends BaseController {

    /**
     * 添加用户跳转
     */
    @RequestMapping("/edit")
    public String add(Model model) {
        SystemConfig systemConfig = SystemConfigUtil.get();
        model.addAttribute("systemConfig", systemConfig);
        return "system_config_edit";
    }

    @InitBinder("systemConfig")
    public void binderSystemConfig(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("systemConfig.");
    }

    @RequestMapping("/update")
    public String update(SystemConfig systemConfig, Model model,
            HttpServletRequest request) {
        SystemConfigUtil.update(systemConfig);
        isRecordLog = true;
        operationName = "修改系统设置";
        logInfo = "修改系统设置信息";
        setLog(request);
        model.addAttribute("url", "/systemConfig/edit");
        return SUCCESS;
    }
}
