package com.cienet.util;

import java.util.Map;

/**
 * @ClassName: LicenseCreate
 * @Description: license文件内容
 * @author 詹涛
 * @date 2014年8月06日 下午1:52:29
 * 
 */
public class LicenseCreate {
	/**
	 * 生成License文件
	 * 
	 * @param name
	 *            文件名
	 * @param otherDate
	 *            其他信息 用于生成公钥/私钥的种子
	 * @param encoding
	 *            编码
	 */
	public void create(String path,String name, Map<String, String> otherData,
			String encoding) {
		try {

			if (null != otherData) {
				for (Map.Entry<String, String> entry : otherData.entrySet()) {
					// //////客户留//////////
					PropertiesUtil.write(path + name + ".license", encoding,
							entry.getKey(), entry.getValue());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
