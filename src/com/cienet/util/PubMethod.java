package com.cienet.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 判断是否为空
 * 
 * huilet 2013-3-25
 * @author yuanc
 */
public class PubMethod {
	/**
	 * 此处需要考虑空模式的应用，传统意义上面不含元素的空数组是不为空的
	 * @return boolean
	 * @deprecated
	 */
	public static boolean isEmpty(Object data) {
		return null == data || data.toString() == "" || "null".equals(data);
	}

	/**
	 * 判断空
	 * @param Value
	 * @return
	 */
	public static boolean isEmpty(String Value) {
		return (Value == null || "-1".equals(Value) || ",".equals(Value) || Value.trim().equals("") || "null".equals(Value.trim()) || "undefined".equals(Value));
	}

	public static boolean isEmpty(StringBuffer Value) {

		return (Value == null || (Value.toString().trim()).equals(""));
	}

	public static boolean isEmpty(List list) {
		if (list == null || list.size() == 0)
			return true;
		else
			return false;
	}

	public static boolean isEmpty(Set set) {
		if (set == null || set.size() == 0)
			return true;
		else
			return false;
	}

	public static boolean isEmpty(Map map) {
		if (map == null || map.size() == 0)
			return true;
		else
			return false;
	}

	public static boolean isEmpty(Double value) {
		if (value == null || value.doubleValue() == 0.0)
			return true;
		else
			return false;
	}

	public static boolean isEmpty(Long obj) {
		if (obj == null || obj.longValue() == 0)
			return true;
		else
			return false;
	}

	/**
	 * 判断大字符串："a,b,c,d,e"中，是否包含字符串 "A"
	 * yuanfang
	 * @param arr
	 * @param str
	 * @return
	 */
	public static boolean contains(String arrStr,String str){
		if(PubMethod.isEmpty(arrStr) || PubMethod.isEmpty(str)){
			return false;
		}
		String[] arr=arrStr.split(",");
		for(int i=0;i<arr.length;i++){
			if(str.equals(arr[i])){
				return true;
			}
		}
		return false;
	}
}
