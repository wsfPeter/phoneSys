package com.cienet.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Description: Spring工具类
 *
 * @author zhujiang
 * @version 1.0
 * @created 2013-3-21 下午04:47:33
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据Bean名称获取实例
     *
     * @param name
     *            Bean注册名称
     * @return bean实例
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * 获取实例
     * 
     * @param name
     *            Bean名称
     * @param type
     *            Bean类型
     * @return 实例
     */
    public static <T> T getBean(String name, Class<T> type) {
        Assert.hasText(name);
        Assert.notNull(type);
        return applicationContext.getBean(name, type);
    }

}