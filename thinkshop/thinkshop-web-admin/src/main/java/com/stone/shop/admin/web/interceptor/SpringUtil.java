package com.stone.shop.admin.web.interceptor;

import org.springframework.web.context.WebApplicationContext;

public class SpringUtil {
	private static WebApplicationContext context = null;

	public static Object getBean(String beanName) {
		WebApplicationContext ctx = getDefaultWebApplicationContext();
		return ctx.getBean(beanName);
	}

	public static WebApplicationContext getDefaultWebApplicationContext() {
		return context;
	}

	public static void setDefaultWebApplicationContext(WebApplicationContext ctx) {
		context = ctx;
	}
}
