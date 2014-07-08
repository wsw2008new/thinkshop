package com.stone.shop.admin.web.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.stone.shop.admin.utils.term.RequestHelper;

public class StartupListener extends ContextLoaderListener implements
		ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		ServletContext context = event.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(context);
		SpringUtil.setDefaultWebApplicationContext(ctx);

		String contextRealPath = context.getRealPath("");
		RequestHelper.setContextRealPath(contextRealPath);
	}

}
