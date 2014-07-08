package com.stone.shop.admin.web.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stone.shop.common.utils.EncryptUtil;

/** Servlet Filter implementation class CodeFilter */
public class LoginFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = 1742148094607290973L;

	/** Default constructor. */
	public LoginFilter() {
	}

	/** @see Filter#init(FilterConfig) */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	/** @see Filter#destroy() */
	@Override
	public void destroy() {
	}

	/** @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain) */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		String inputCode = httpRequest.getParameter("validateCode");
		String code = (String) session.getAttribute("loginvalidateCode");
		inputCode = EncryptUtil.md5Hex(inputCode);
//		if (!"".equals(code) && code != null) {
//			if (code.equals(inputCode)) {
				chain.doFilter(request, response);
//			} else {
//				JscnLogger.warn("输入的验证码 [" + inputCode + "] 与产生的验证码 [" + code
//						+ "] 不匹配！", this.getClass());
//				httpResponse.sendRedirect(httpRequest.getContextPath()
//						+ "/login.htm?error=validate_error");
//			}
//		} else {
//			JscnLogger.warn("未输入认证码！", this.getClass());
//			httpResponse.sendRedirect(httpRequest.getContextPath()
//					+ "/login.htm?error=no_validate");
//		}
	}

}
