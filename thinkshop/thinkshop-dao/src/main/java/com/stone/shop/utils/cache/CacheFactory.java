package com.stone.shop.utils.cache;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class CacheFactory {

	private static ICache cacheUtil;

	public static ICache buildCacheUtil() {
		if (cacheUtil == null) {
			ApplicationContext ac = new FileSystemXmlApplicationContext(
					"classpath:config/Xmemcached.xml");
			cacheUtil = (ICache) ac.getBean("iCache");
		}
		return cacheUtil;
	}

}
