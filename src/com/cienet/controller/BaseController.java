package com.cienet.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.cienet.bean.Pager;

@Controller
public class BaseController {

    protected Pager pager;

    /**
     * @Fields logInfo : 日志信息
     */
    protected String logInfo;

    /**
     * @Fields isRecordLog : 日志记录标识符
     */
    protected Boolean isRecordLog = false;

    /**
     * @Fields operationName : 操作名称
     */
    protected String operationName;

    /**
     * @Fields SUCCESS : 操作成功跳转页面
     */
    protected String SUCCESS = "success";

    @InitBinder("pager")
    public void binderPager(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("pager.");
    }

    public Boolean getIsRecordLog() {
        return isRecordLog;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public String getOperationName() {
        return operationName;
    }

    public Pager getPager() {
        return pager;
    }

    @RequestMapping(value = "/")
    public String loginRedirect() {
        return "login";
    }

    protected void setErrorInfo(String errorInfo) {
        RequestAttributes requestAttributes = RequestContextHolder
                .currentRequestAttributes();
        requestAttributes.setAttribute("content", errorInfo,
                RequestAttributes.SCOPE_REQUEST);
    }
    
    protected void setErrorInfo(String errorInfo,String url) {
        RequestAttributes requestAttributes = RequestContextHolder
                .currentRequestAttributes();
        requestAttributes.setAttribute("content", errorInfo,
                RequestAttributes.SCOPE_REQUEST);
        requestAttributes.setAttribute("url", url,
                RequestAttributes.SCOPE_REQUEST);

    }

    public void setIsRecordLog(Boolean isRecordLog) {
        this.isRecordLog = isRecordLog;
    }

    protected void setLog(HttpServletRequest request) {
        request.setAttribute("isRecordLog", isRecordLog);
        request.setAttribute("operationName", operationName);
        request.setAttribute("logInfo", logInfo);
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }
}
