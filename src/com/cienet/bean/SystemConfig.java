package com.cienet.bean;

import java.io.Serializable;

/**
 * @ClassName: SystemConfig
 * @Description: Bean类 - 系统配置
 * @author zhujiang
 * @date 2014年6月29日 下午1:54:31
 * 
 */
public class SystemConfig implements Serializable {

    private static final long serialVersionUID = 5717431348476213610L;

    private String defaultPassword;

    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

}