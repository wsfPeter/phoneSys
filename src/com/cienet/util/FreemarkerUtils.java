package com.cienet.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * Utils - Freemarker
 * 
 */
public final class FreemarkerUtils {
    /**
     * 不可实例化
     */
    private FreemarkerUtils() {
    }

    /**
     * 解析字符串模板
     * 
     * @param template
     *            字符串模板
     * @param model
     *            数据
     * @return 解析后内容
     */
    public static String process(String template, Map<String, ?> model)
            throws IOException, TemplateException {
        Configuration configuration = null;
        ApplicationContext applicationContext = SpringUtil
                .getApplicationContext();
        if (applicationContext != null) {
            FreeMarkerConfigurer freeMarkerConfigurer = (FreeMarkerConfigurer) SpringUtil
                    .getBean("freeMarkerConfigurer");
            if (freeMarkerConfigurer != null) {
                configuration = freeMarkerConfigurer.getConfiguration();
            }
        }
        return process(template, model, configuration);
    }

    /**
     * 解析字符串模板
     * 
     * @param template
     *            字符串模板
     * @param model
     *            数据
     * @param configuration
     *            配置
     * @return 解析后内容
     */
    public static String process(String template, Map<String, ?> model,
            Configuration configuration) throws IOException, TemplateException {
        if (template == null) {
            return null;
        }
        if (configuration == null) {
            configuration = new Configuration();
        }
        StringWriter out = new StringWriter();
        new Template("template", new StringReader(template), configuration)
                .process(model, out);
        return out.toString();
    }

    /**
     * 获取变量
     * 
     * @param name
     *            名称
     * @param env
     *            Environment
     * @return 变量
     */
    public static TemplateModel getVariable(String name, Environment env)
            throws TemplateModelException {
        Assert.hasText(name);
        Assert.notNull(env);
        return env.getVariable(name);
    }

    /**
     * 设置变量
     * 
     * @param name
     *            名称
     * @param value
     *            变量值
     * @param env
     *            Environment
     */
    public static void setVariable(String name, Object value, Environment env)
            throws TemplateException {
        Assert.hasText(name);
        Assert.notNull(env);
        if (value instanceof TemplateModel) {
            env.setVariable(name, (TemplateModel) value);
        } else {
            env.setVariable(name, ObjectWrapper.BEANS_WRAPPER.wrap(value));
        }
    }

    /**
     * 设置变量
     * 
     * @param variables
     *            变量
     * @param env
     *            Environment
     */
    public static void setVariables(Map<String, Object> variables,
            Environment env) throws TemplateException {
        Assert.notNull(variables);
        Assert.notNull(env);
        for (Entry<String, Object> entry : variables.entrySet()) {
            String name = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof TemplateModel) {
                env.setVariable(name, (TemplateModel) value);
            } else {
                env.setVariable(name, ObjectWrapper.BEANS_WRAPPER.wrap(value));
            }
        }
    }

}