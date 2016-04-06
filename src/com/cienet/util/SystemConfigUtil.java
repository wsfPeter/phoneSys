package com.cienet.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.core.io.ClassPathResource;

import com.cienet.bean.SystemConfig;

/**
 * Description:系统配置工具类
 * 
 * @author zhujiang
 * @version 1.0
 * @created 2013-4-9 上午10:53:13
 */
public class SystemConfigUtil {

    /**
     * @Title: getSystemConfig
     * @Description: 获取系统配置信息
     * @return SystemConfig 系统配置信息
     */
    @SuppressWarnings("unchecked")
    public static SystemConfig get() {
        SystemConfig systemConfig = (SystemConfig) EhCacheConfigUtil
                .getFromCache(SYSTEM_CONFIG_CACHE_KEY);

        if (systemConfig == null) {
            try {
                systemConfig = new SystemConfig();

                File configFile = new ClassPathResource(CONFIG_FILE_NAME)
                        .getFile();
                Document document = new SAXReader().read(configFile);
                List<Element> elements = document.selectNodes("/exam/setting");
                for (Element element : elements) {
                    String name = element.attributeValue("name");
                    String value = element.attributeValue("value");
                    try {
                        beanUtils.setProperty(systemConfig, name, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

                EhCacheConfigUtil.putInCache(SYSTEM_CONFIG_CACHE_KEY,
                        systemConfig);
            } catch (Exception e) {
                logger.error("获取系统配置信息失败", e);
            }
        }
        return systemConfig;
    }

    /**
     * @Title: updateSystemConfig
     * @Description: 更新系统配置
     * @param systemConfig
     *            系统配置信息
     */
    @SuppressWarnings("unchecked")
    public static void update(SystemConfig systemConfig) {
        try {
            File configFile = new ClassPathResource(CONFIG_FILE_NAME).getFile();
            Document document = new SAXReader().read(configFile);
            List<Element> elements = document.selectNodes("/exam/setting");
            for (Element element : elements) {
                try {
                    String name = element.attributeValue("name");
                    String value = beanUtils.getProperty(systemConfig, name);
                    Attribute attribute = element.attribute("value");
                    attribute.setValue(value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }

            FileOutputStream fileOutputStream = null;
            XMLWriter xmlWriter = null;
            try {
                OutputFormat outputFormat = OutputFormat.createPrettyPrint();
                outputFormat.setEncoding("UTF-8");
                outputFormat.setIndent(true);
                outputFormat.setIndent("    ");
                outputFormat.setNewlines(true);
                fileOutputStream = new FileOutputStream(configFile);
                xmlWriter = new XMLWriter(fileOutputStream, outputFormat);
                xmlWriter.write(document);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (xmlWriter != null) {
                    try {
                        xmlWriter.close();
                    } catch (IOException e) {
                    }
                }
                IOUtils.closeQuietly(fileOutputStream);
            }

            EhCacheConfigUtil.putInCache(SYSTEM_CONFIG_CACHE_KEY, systemConfig);
        } catch (Exception e) {
            logger.error("更新系统配置信息失败", e);
        }
    }

    /** BeanUtilsBean */
    private static final BeanUtilsBean beanUtils = new BeanUtilsBean(
            new ConvertUtilsBean());

    private static Logger logger = Logger.getLogger(SystemConfigUtil.class);

    /**
     * @Fields CONFIG_FILE_NAME : 系统配置文件名称
     */
    public static final String CONFIG_FILE_NAME = "/config.xml";

    /**
     * @Fields SYSTEM_CONFIG_CACHE_KEY : systemConfig缓存Key
     */
    public static final String SYSTEM_CONFIG_CACHE_KEY = "systemConfig";

}