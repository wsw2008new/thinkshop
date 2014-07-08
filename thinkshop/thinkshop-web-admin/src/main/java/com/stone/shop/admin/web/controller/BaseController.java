package com.stone.shop.admin.web.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stone.shop.admin.utils.exception.WebException;
import com.stone.shop.admin.utils.log.JscnLogger;
import com.stone.shop.admin.web.security.UserInfo;
import com.stone.shop.base.common.exception.SystemException;
import com.stone.shop.base.common.utils.EncryptUtil;
import com.stone.shop.base.common.utils.StringUtils;

@Controller
public class BaseController {

	public static final String LOGIN = "page/login";

	public static final String INDEX = "page/index";

	public static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));

	}

	@RequestMapping("login")
	public String ToLogin(String error, ModelMap model)
			throws UnsupportedEncodingException {
		if (StringUtils.isNotEmpty(error)) {
			if ("no_validate".equals(error)) {
				error = "请输入验证码";
			} else if ("validate_error".equals(error)) {
				error = "验证码错误，请重新输入";
			}
		}
		model.addAttribute("error", error);

		return LOGIN;
	}

	@RequestMapping("index")
	public String ToIndex() {
		return INDEX;
	}

	@RequestMapping("header")
	public String ToHeader() {
		return "page/main/header1";
	}

	@RequestMapping("content")
	public String ToContent() {
		return "page/main/content1";
	}

	@RequestMapping("footer")
	public String ToFooter() {
		return "page/main/footer1";
	}

	@RequestMapping("middel")
	public String ToMiddel() {
		return "page/main/middel1";
	}

	@RequestMapping("default")
	public String ToDefault() {
		return "page/default";
	}

	@RequestMapping("accessdenied")
	public String ToAccessdenied() {
		return "page/accessdenied";
	}

	@RequestMapping("getValidateCode")
	public String getValidateCode(HttpServletRequest request,
			HttpServletResponse response) throws WebException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		int width = 60, height = 20;
		BufferedImage pic = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics gc = pic.getGraphics();

		gc.setColor(getRandColor(200, 250));
		gc.fillRect(0, 0, width, height);

		gc.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		gc.setColor(getRandColor(160, 200));

		Random r = new Random();
		for (int i = 0; i < 200; i++) {
			int x1 = r.nextInt(width);
			int y1 = r.nextInt(height);
			int x2 = r.nextInt(15);
			int y2 = r.nextInt(15);
			gc.drawLine(x1, y1, x1 + x2, y1 + y2);
		}
		gc.setColor(getRandColor(120, 240));
		for (int i = 0; i < 100; i++) {
			int x = r.nextInt(width);
			int y = r.nextInt(height);
			gc.drawOval(x, y, 0, 0);
		}
		String rn;
		String RS = "";
		for (int i = 0; i < 4; i++) {
			rn = String.valueOf(r.nextInt(10));
			RS += rn;
			gc.setColor(new Color(20 + r.nextInt(110), 20 + r.nextInt(110),
					20 + r.nextInt(110)));
			gc.drawString(rn, 13 * i + 6, 16);
		}
		gc.dispose();
		RS = EncryptUtil.md5Hex(RS);
		request.getSession().setAttribute("loginvalidateCode", RS);
		ServletOutputStream output = null;
		try {
			output = response.getOutputStream();
			ImageIO.write(pic, "JPEG", output);
			output.flush();
		} catch (IOException e) {
			JscnLogger.error("生成验证码时产生异常：" + e.getMessage(), this.getClass());
			throw new WebException("生成验证码失败！");
		} finally {
			try {
				if (output != null)
					output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	protected UserInfo getUserInfo(HttpServletRequest request) {
		SecurityContext securityContext = null;

		if (request.getSession().getAttribute(SPRING_SECURITY_CONTEXT) != null)
			securityContext = (SecurityContext) request.getSession()
					.getAttribute(SPRING_SECURITY_CONTEXT);

		if (securityContext == null) {
			JscnLogger.warn("SecurityContext is not exist!", this.getClass());
			return null;
		}

		Authentication authentication = securityContext.getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null) {
			UserInfo userInfo = (UserInfo) authentication.getPrincipal();
			JscnLogger.info("URI [" + request.getRequestURI()
					+ "] is invoked by " + "User [" + userInfo.getUsername()
					+ "]", this.getClass());
			return userInfo;
		}
		JscnLogger.warn("Can not get user information from request!",
				this.getClass());
		return null;
	}

	protected void getValidError(BindingResult result) throws SystemException {
		if (result.hasErrors()) {
			String message = "";
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				if (error == null) {
					continue;
				}
				message = message + error.getDefaultMessage();
			}
			JscnLogger.error("Nested path [" + result.getNestedPath()
					+ "] has errors [" + message + "]", this.getClass());
			throw new SystemException(message);
		}
	}

	protected String getBasePath(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath() + "/";
	}

	private Color getRandColor(int fc, int bc) {
		Random r = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int red = fc + r.nextInt(bc - fc);
		int green = fc + r.nextInt(bc - fc);
		int blue = fc + r.nextInt(bc - fc);
		return new Color(red, green, blue);
	}
}
