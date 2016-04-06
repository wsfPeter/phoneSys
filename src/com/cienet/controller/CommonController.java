package com.cienet.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;

import com.cienet.service.AdminService;

@Controller
@RequestMapping(value = "/common")
public class CommonController extends BaseController implements
        ServletContextAware {

	@Autowired
    private AdminService adminService;
	
    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * 首页
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("javaVersion", System.getProperty("java.version"));
        model.addAttribute("javaHome", System.getProperty("java.home"));
        model.addAttribute("osName", System.getProperty("os.name"));
        model.addAttribute("osArch", System.getProperty("os.arch"));
        model.addAttribute("serverInfo", servletContext.getServerInfo());
        model.addAttribute("servletVersion", servletContext.getMajorVersion()
                + "." + servletContext.getMinorVersion());
        return "index";
    	
    }
}