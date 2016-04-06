package com.cienet.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @ClassName: EhCacheConfigUtil
 * @Description: 缓存工具类
 * @author zhujiang
 * @date 2014年6月29日 下午1:52:29
 * 
 */
public class EhCacheConfigUtil {

    /**
     * 根据Key读取缓存
     * 
     * @return 缓存对象
     */
    public static Object getFromCache(String key) {
        CacheManager cacheManager = CacheManager.create();
        Cache systemConfigCache = cacheManager.getCache(SYSTEM_CONFIG_CACHE);
        Element element = systemConfigCache.get(key);
        if (element == null) {
            return null;
        } else {
            return systemConfigCache.get(key).getValue();
        }
    }

    /**
     * 加入对象到缓存
     * 
     */
    public static void putInCache(String key, Object object) {
        CacheManager cacheManager = CacheManager.create();
        Cache systemConfigCache = cacheManager.getCache(SYSTEM_CONFIG_CACHE);
        systemConfigCache.put(new Element(key, object));
    }

    public static final String SYSTEM_CONFIG_CACHE = "systemConfigCache";

}