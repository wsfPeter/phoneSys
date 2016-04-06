package com.cienet.util;

import java.io.IOException;
import java.io.Writer;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * Description: Json工具类
 *
 * @author zhujiang
 * @version 1.0
 * @created 2013-3-21 下午04:47:09
 */
public class JsonUtil {

    public static ObjectMapper getMapper() {
        return (ObjectMapper) SpringUtil.getBean("jacksonObjectMapper");
    }

    public static String toString(Object obj) {
        ObjectMapper objectmapper = getMapper();
        try {
            return objectmapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object toObject(String s, Class<?> class1) {
        ObjectMapper objectmapper = getMapper();
        try {
            return objectmapper.readValue(s, class1);
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getValue(String s, TypeReference<?> typereference) {

        ObjectMapper objectmapper = getMapper();
        try {
            return objectmapper.readValue(s, typereference);
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将对象转换为JSON流
     * 
     * @param writer
     *            writer
     * @param value
     *            对象
     */
    public static void writeValue(Writer writer, Object value) {
        try {
            ObjectMapper objectmapper = getMapper();
            objectmapper.writeValue(writer, value);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getGUID() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }

}
