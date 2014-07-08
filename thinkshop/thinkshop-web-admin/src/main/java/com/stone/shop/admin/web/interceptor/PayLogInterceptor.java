package com.stone.shop.admin.web.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.stone.shop.admin.service.manage.log.ILogService;
import com.stone.shop.admin.web.controller.BaseController;
import com.stone.shop.admin.web.security.UserInfo;
import com.stone.shop.base.common.page.PageUrlUtil;
import com.stone.shop.base.common.utils.StringUtils;
import com.stone.shop.domain.manage.log.LogEntity;

public class PayLogInterceptor extends BaseController implements
		HandlerInterceptor {

	@Autowired
	private ILogService logService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		LogEntity log = new LogEntity();
		log.setId(StringUtils.getUUID());
		request.getSession().getAttribute("");
		UserInfo userInfo = getUserInfo(request);
		if (userInfo == null) {
			return true;
		}
		if (userInfo.getOperator() == null) {
			return true;
		}
		log.setOperatorId(userInfo.getOperator().getOperatorId());
		log.setOperatorName(userInfo.getOperator().getName());
		log.setIp(request.getRemoteAddr());
		log.setTime(new Date());
		log.setContent("执行操作:"
				+ PageUrlUtil.getPageUrl(request.getParameterMap(),
						request.getRequestURI()));
		logService.addLog(log);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}
}
