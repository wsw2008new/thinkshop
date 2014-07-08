package com.stone.shop.admin.web.controller.role;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stone.shop.admin.service.manage.role.IOperatorService;
import com.stone.shop.admin.service.manage.role.IRoleService;
import com.stone.shop.admin.utils.exception.WebException;
import com.stone.shop.admin.utils.log.JscnLogger;
import com.stone.shop.admin.web.controller.BaseController;
import com.stone.shop.base.common.page.Pagination;
import com.stone.shop.base.common.utils.EncryptUtil;
import com.stone.shop.base.common.utils.StringUtils;
import com.stone.shop.domain.manage.role.OperatorEntity;
import com.stone.shop.domain.manage.role.RoleEntity;
import com.stone.shop.domain.persistent.ResultList;

@Controller
@RequestMapping("/operator")
public class OperatorController extends BaseController {

	@Autowired
	private IOperatorService operatorService;

	@Autowired
	private IRoleService roleService;

	@RequestMapping("toaddoperator")
	public String toAddOperator(ModelMap model)
			throws WebException {
		try {
			ResultList<RoleEntity> rolesRes = roleService
					.queryRoles(null, null);
			model.addAttribute("roles", rolesRes.getResults());

		} catch (Exception e) {
			JscnLogger.error("获得信息失败", e, this.getClass());
			throw new WebException("系统错误");
		}
		return "page/main/role/addoperator";
	}

	/**
	 * 根据账号名查询操作员信息
	 * @param operatorId 账号
	 * @param model 模型
	 * @return 返回值
	 * @throws WebException 异常
	 **/
	@RequestMapping("tomodifyoperator")
	public String toModifyOperator(String operatorId, ModelMap model)
			throws WebException {
		try {
			OperatorEntity operator = operatorService
					.getOperatorById(operatorId);
			if (operator != null) {
				model.addAttribute("operator", operator);
			}
		} catch (Exception e) {
			JscnLogger.error("获得操作员信息失败", e, this.getClass());
			throw new WebException("获得操作员信息失败");
		}
		return "page/main/role/modifyoperator";
	}

	/**
	 * 根据账号名查询账户是否存在
	 * @param operatorId 账号
	 * @return 返回值
	 * @throws WebException 异常
	 **/
	@RequestMapping("isoperatorexist")
	public @ResponseBody
	Map<String, String> isOperatorExist(String operatorId) throws WebException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			OperatorEntity operator = operatorService
					.getOperatorById(operatorId);
			if (operator != null) {
				map.put("message", "1");
			} else {
				map.put("message", "0");
			}
		} catch (Exception e) {
			JscnLogger.error("获得操作员信息失败", e, this.getClass());
			throw new WebException("获得操作员信息失败");
		}
		return map;

	}

	/**
	 * 根据账号名查询账户密码是否一致
	 * @param operatorId 账号
	 * @param password 密码
	 * @return 返回值
	 * @throws WebException 异常
	 **/
	@RequestMapping("isoperatorpwd")
	public @ResponseBody
	Map<String, String> isOperatorpwdById(String operatorId, String password)
			throws WebException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			// System.out.println(StringUtil.md5Base64(password));
			OperatorEntity operator = operatorService
					.getOperatorById(operatorId);
			if (operator.getPassword().equals(EncryptUtil.md5Hex(password))) {
				map.put("message", "1");
			} else {
				map.put("message", "0");
			}
		} catch (Exception e) {
			JscnLogger.error("获得操作员信息失败", e, this.getClass());
			throw new WebException("获得操作员信息失败");
		}
		return map;
	}

	/**
	 * 修改操作员信息
	 * @param operator 操作者
	 * @param result 结果
	 * @param request 请求
	 * @param response 回应
	 * @param model 模型
	 * @return 返回值
	 * @throws WebException Web异常
	 */
	@RequestMapping("modifyoperator")
	public String modifyOperator(@Valid OperatorEntity operator,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws WebException {
		try {
			if (operator != null) {
				operatorService.modifyOperator(operator);
			}
			getValidError(result);
			response.sendRedirect("searchoperators.htm");
		} catch (Exception e) {
			JscnLogger.error("修改操作员信息失败", e, this.getClass());
			throw new WebException("数据错误：" + e.getLocalizedMessage());
		}

		return null;
	}

	/**
	 * 修改操作员密码
	 * @param operatorId 操作者
	 * @param password 密码
	 * @param prepassword 原密码
	 * @param request 请求
	 * @param response 回应
	 * @param model 模型
	 * @return 返回值
	 * @throws WebException Web异常
	 */
	@RequestMapping("modifypwd")
	public String modifyPwd(String operatorId, String password,
			String prepassword, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws WebException {
		try {

			if (StringUtils.isEmpty(operatorId)) {
				throw new WebException("操作员账号不能为空");
			}
			if (StringUtils.isEmpty(prepassword)) {
				throw new WebException("原密码不能为空");
			}
			if (StringUtils.isEmpty(password)) {
				throw new WebException("新密码不能为空");
			}
			operatorService
					.modifyOperatorPwd(operatorId, password, prepassword);
			response.sendRedirect("searchoperators.htm");

		} catch (SystemException e) {
			throw new WebException(e.getMessage());
		} catch (IOException e) {
			JscnLogger.error("修改操作员密码失败", e, this.getClass());
			throw new WebException("修改操作员密码失败");
		}

		return null;
	}

	/**
	 * 添加操作员信息
	 * @param operator 操作者
	 * @param result 结果
	 * @param roleCodes 角色code
	 * @param request 请求
	 * @param response 回应
	 * @param model 模型
	 * @return 返回值
	 * @throws IOException IO异常
	 * @throws WebException Web异常
	 */
	@RequestMapping("addoperator")
	public String addOperator(@Valid OperatorEntity operator,
			BindingResult result, String[] roleCodes,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws WebException, IOException {
		try {
			getValidError(result);
			if (operator != null) {
				String pa = EncryptUtil.md5Hex(operator.getPassword());
				operator.setPassword(pa);
				operator.setLastTime(request.getRemoteAddr());
				operator.setLastIp(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				operatorService.addOperator(operator, roleCodes);
			}
			response.sendRedirect("searchoperators.htm");
		} catch (SystemException e) {
			JscnLogger.error("添加操作员失败", e, this.getClass());
			throw new WebException("数据错误：" + e.getLocalizedMessage());
		}

		return null;
	}

	/**
	 * 根据ID删除操作员信息
	 * @param operatorId 操作者
	 * @param operatorId1 操作者
	 * @param name 姓名
	 * @param status 状态
	 * @param page 分页
	 * @param request 请求
	 * @param response 回应
	 * @param model 模型
	 * @return 返回值
	 * @throws WebException Web异常
	 */
	@RequestMapping("deleteoperator")
	public String deleteOperator(String operatorId, String operatorId1,
			String name, String status, Pagination page,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws WebException {
		try {
			operatorService.deleteOperator(operatorId);
			ResultList<OperatorEntity> res = operatorService.queryOperators(
					operatorId1, name, status, page);
			model.addAttribute("operators", res);
			model.addAttribute("operatorId", operatorId1);
			model.addAttribute("name", name);
			model.addAttribute("status", status);
		} catch (Exception e) {
			JscnLogger.error("删除操作员失败", e, this.getClass());
			throw new WebException("删除操作员失败");
		}
		return "page/main/role/operatorlist";
	}

	/**
	 * 删除已选的操作员信息
	 * @param ids 选中操作者
	 * @param request 请求
	 * @param response 回应
	 * @param model 模型
	 * @return 返回值
	 * @throws WebException Web异常
	 */
	@RequestMapping("deleteoperators")
	public String deleteOperators(String[] ids, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws WebException {
		try {
			String url = request.getHeader("Referer");
			operatorService.deleteOperators(ids);
			response.sendRedirect("searchoperators.htm");
			response.sendRedirect(url);
		} catch (Exception e) {
			JscnLogger.error("删除操作员失败", e, this.getClass());
			throw new WebException("删除操作员失败");
		}
		return null;
	}

	/**
	 * 根据账号名或姓名搜索操作员信息
	 * @param operatorId 操作者
	 * @param name 姓名
	 * @param status 状态
	 * @param page 分页
	 * @param request 请求
	 * @param response 回应
	 * @param model 模型
	 * @return 返回值
	 * @throws WebException Web异常
	 */
	@RequestMapping("searchoperators")
	public String searchOperators(String operatorId, String name,
			String status, Pagination page, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws WebException {
		try {
			ResultList<OperatorEntity> res = operatorService.queryOperators(
					operatorId, name, status, page);
			model.addAttribute("operators", res);
			model.addAttribute("operatorId", operatorId);
			model.addAttribute("name", name);
			model.addAttribute("status", status);
		} catch (Exception e) {
			JscnLogger.error("查询操作员失败", e, this.getClass());
			throw new WebException("查询操作员失败");
		}
		return "page/main/role/operatorlist";
	}

	/**
	 * 根据账号名查询操作员详细信息
	 * @param operatorId 操作者
	 * @param model 模型
	 * @return 返回值
	 * @throws WebException Web异常
	 */
	@RequestMapping("getoperator")
	public String getOperator(String operatorId, ModelMap model)
			throws WebException {
		try {
			OperatorEntity operator = operatorService
					.getOperatorById(operatorId);
			if (operator != null) {
				model.addAttribute("operator", operator);
			}
		} catch (Exception e) {
			JscnLogger.error("查询操作员失败", e, this.getClass());
			throw new WebException("查询操作员失败");
		}
		return "page/main/role/information";
	}

	/**
	 * 根据账号名查询操作员信息修改密码
	 * @param operatorId 操作者
	 * @param model 模型
	 * @return 返回值
	 * @throws WebException Web异常
	 */
	@RequestMapping("tomodifypwd")
	public String toModifyPwd(String operatorId, ModelMap model)
			throws WebException {
		try {
			OperatorEntity operator = operatorService
					.getOperatorById(operatorId);
			if (operator != null) {
				model.addAttribute("operator", operator);
				model.addAttribute("message", 1);
			} else {
				model.addAttribute("message", 0);
			}
		} catch (Exception e) {
			JscnLogger.error("查询操作员失败", e, this.getClass());
			throw new WebException("查询操作员失败");
		}
		return "page/main/role/modifypwd";
	}
}
