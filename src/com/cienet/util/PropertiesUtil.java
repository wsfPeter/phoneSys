package com.cienet.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName: PropertiesUtil
 * @Description: license文件生成工具类
 * @author 詹涛
 * @date 2014年8月06日 下午1:52:29
 * 
 */
public class PropertiesUtil {

	private static Properties pps;

	public static Properties getPps() {
		return pps;
	}

	public static void initProperties(String filePath) {
		initProperties(filePath, "utf-8");
	}

	public static void initProperties(String filePath, String encoding) {
		Writer writer = null;
		try {
			pps = new Properties();
			// 判断文件是否存在，如果不存在则创建文件
			File file = new File(filePath);
			File dirFile = new File(file.getParent());
			if (!dirFile.exists()) {
				boolean md = dirFile.mkdirs();
				if (md) {
					System.out.println("directory created");
				} else {
					System.out.println("directory and parent's created");
				}
			}
			if (!file.exists()) {
				boolean cnf = file.createNewFile();
				if (cnf) {
				} else {
					System.out.println("had created file");
				}
			}
			Reader rd = new InputStreamReader(new FileInputStream(file),
					encoding);
			// 加载properties文件
			pps.load(rd);
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != writer) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String readValue(String filePath, String name) {
		return readValue(filePath, "utf-8", name);
	}

	/**
	 * Retrieve KEY by Value
	 * 
	 * @param filePath
	 * @param encoding
	 * @param name
	 * @return
	 */
	public static String readValue(String filePath, String encoding, String name) {
		initProperties(filePath, encoding);
		return pps.getProperty(name);
	}

	/**
	 * Write Resource File
	 * 
	 * @param filePath
	 * @param name
	 * @param value
	 */
	public static void write(String filePath, String name, String value) {
		write(filePath, "utf-8", name, value);
	}

	/**
	 * 写资源文件
	 * 
	 * @param filePath
	 * @param encoding
	 * @param name
	 * @param value
	 */
	public static void write(String filePath, String encoding, String name,
			String value) {
		try {
			initProperties(filePath, encoding);
			pps.setProperty(name, value);
			Writer osw = new OutputStreamWriter(new FileOutputStream(filePath),
					encoding);
			// 以适合使用load方法加载到Properties表中的格式，将此Properties表中的属性列表（键和元素对）写入输出流
			// props.store(fos, "Update '" + parameterName + "' value");
			pps.store(osw, "");
			osw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 
	 * @param filePath
	 */
	public static Map<String, String> readAll(String filePath) {
		return readAll(filePath, "utf-8");
	}

	/**
	 * read all informatin in the properties
	 * 
	 * @param filePath
	 * @param encoding
	 */
	public static HashMap<String, String> readAll(String filePath,
			String encoding) {
		HashMap<String, String> map = new HashMap<String, String>();
		initProperties(filePath, encoding);

		// 获取所有的key，放入枚举类型中
		Enumeration en = pps.propertyNames();
		// 遍历枚举，根据key取出value
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			String property = pps.getProperty(key);
			// 将值放入key中
			map.put(key, property);
		}
		return map;
	}
}
