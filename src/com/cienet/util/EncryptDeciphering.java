package com.cienet.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

/**
 * @ClassName: encryptDeciphering
 * @Description: license文件加密 解密 获取文件MD5工具类
 * @author 詹涛
 * @date 2014年8月09日 下午1:52:29
 * 
 */
public class EncryptDeciphering {

    /**
     * 加密
     * 
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encrypt(String str)
            throws UnsupportedEncodingException {
        return new String(Base64.encodeBase64(str.getBytes()), "UTF-8");
    }

    /**
     * 获取文件的MD5码
     * 
     * @param inputStream
     * @return
     */
    public static String streamToMD5(InputStream inputStream) {
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int numRead = 0;
            while ((numRead = inputStream.read(buffer)) > 0) {
                mdTemp.update(buffer, 0, numRead);
            }
            return toHexString(mdTemp.digest());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取文件的MD5码
     * 
     * @param inputStream
     * @return
     */
    private static String toHexString(byte[] md) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        int j = md.length;
        char str[] = new char[j * 2];
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[2 * i] = hexDigits[byte0 >>> 4 & 0xf];
            str[i * 2 + 1] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }
}
