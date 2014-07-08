package com.stone.shop.admin.web.controller.role;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

import com.stone.shop.admin.web.controller.BaseController;
import com.stone.shop.admin.web.security.MyInvocationSecurityMetadataSource;
import com.stone.shop.common.page.Pagination;
import com.stone.shop.domain.manage.role.OperatorEntity;
import com.stone.shop.domain.manage.role.RoleEntity;
import com.stone.shop.domain.persistent.ResultList;
import com.stone.shop.service.manage.role.IOperatorRoleService;
import com.stone.shop.service.manage.role.IRoleService;
import com.stone.shop.utils.exception.WebException;
import com.stone.shop.utils.log.JscnLogger;

@Controller
@RequestMapping("/rolegl")
public class RoleController extends BaseController {

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IOperatorRoleService operatorRoleService;
	
	@Autowired
	private MyInvocationSecurityMetadataSource source;

	/**
	 * 根据名称搜索角色信息
	 * @param title 标题
	 * @param page 分页
	 * @param request 请求
	 * @param response 回应
	 * @param model 模型
	 * @return 角色信息列表
	 * 
	 * @throws WebException 异常
	 * */
	@RequestMapping("queryroles")
	public String searchRoles(String title, Pagination page,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws WebException {
		try {
			ResultList<RoleEntity> res = roleService.queryRoles(title, page);
			model.addAttribute("roles", res);
			model.addAttribute("title", title);
		} catch (Exception e) {
			JscnLogger.error("查询角色失败", e, this.getClass());
			throw new WebException("查询角色失败");
		}
		return "page/main/role/rolelist";
	}

	/**
	 * 根据编码查询角色信息
	 * @param code 编码
	 * @param model 模型
	 * @return 返回
	 * 
	 * @throws WebException 异常
	 * */
	@RequestMapping("tomodifyrole")
	public String toModifyRole(String code, ModelMap model) throws WebException {
		try {
			RoleEntity role = roleService.getRole(code);
			if (role != null) {
				model.addAttribute("role", role);

			}
		} catch (Exception e) {
			JscnLogger.error("获得角色失败", e, this.getClass());
			throw new WebException("获得角色失败");
		}
		return "page/main/role/modifyrole";
	}

	/**
	 * 修改角色信息
	 * @param role 角色
	 * @param result 结果
	 * @param request 请求
	 * @param response 回应
	 * @param model 模型
	 * @return 返回
	 * @throws WebException 异常
	 * @throws IOException 异常
	 * */
	@RequestMapping("modifyrole")
	public String modifyRole(@Valid RoleEntity role, BindingResult result,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws WebException, IOException {
		try {
			String url=request.getHeader("Referer");
			if (role != null) {
				roleService.modifyRole(role);
				source.loadResourceDefine();
			}
			getValidError(result);
			response.sendRedirect(url);
		} catch (Exception e) {
			JscnLogger.error("修改角色失败", e, this.getClass());
			throw new WebException("数据错误：" + e.getLocalizedMessage());
		}

		return null;
	}

	/**
	 * 跳转到角色添加页面
	 * 
	 * @return 返回
	 * */
	@RequestMapping("toaddrole")
	public String toAddRole() {
		return "page/main/role/addrole";
	}

	/**
	 * 根据角色编码查询角色是否存在
	 * @param code 编码
	 * @return 返回
	 * @throws WebException 异常
	 * */
	@RequestMapping("isroleexist")
	public @ResponseBody
	Map<String, String> isRoleExist(String code) throws WebException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			RoleEntity role = roleService.getRole(code);
			if (role != null) {
				map.put("message", "1");
			} else {
				map.put("message", "0");
			}
		} catch (Exception e) {
			JscnLogger.error("获得角色失败", e, this.getClass());
			throw new WebException("获得角色失败");
		}
		return map;

	}

	/**
	 * 添加角色信息
	 * @param role 角色
	 * @param result 结果
	 * @param request 请求
	 * @param response 回应
	 * @param model 模型
	 * @return 返回
	 * @throws WebException 异常
	 * @throws IOException 异常
	 * */
	@RequestMapping("addrole")
	public String addRole(@Valid RoleEntity role, BindingResult result,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws WebException, IOException {
		try {
			String url=request.getHeader("Referer");
			getValidError(result);
			if (role != null) {
				roleService.addRole(role);
				source.loadResourceDefine();
			}
			
			response.sendRedirect(url);
		} catch (SystemException e) {
			JscnLogger.error("添加角色失败", e, this.getClass());
			throw new WebException("数据错误：" + e.getLocalizedMessage());
		}

		return null;
	}

	/**
	 * 删除角色信息及对应的操作员，菜单信息
	 * @param code 编码
	 * @param request 请求
	 * @param response 回应
	 * @param model 模型
	 * @return 返回
	 * @throws IOException 异常
	 * @throws WebException 异常
	 * */
	@RequestMapping("deleterole")
	public String deleteRole(String code, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException,
			WebException {
		try {
			roleService.deleteRoles(new String[] { code });
			source.loadResourceDefine();
			response.sendRedirect("queryroles.htm");
		} catch (Exception e) {
			JscnLogger.error("删除角色失败", e, this.getClass());
			throw new WebException("删除角色失败");
		}
		return null;
	}

	/**
	 * 删除所选角色信息及对应的操作员，菜单信息
	 * @param ids 编号
	 * @param request 请求
	 * @param response 回应
	 * @param model 模型
	 * @return 返回
	 * @throws WebException 异常
	 * */
	@RequestMapping("deleteroles")
	public String deleteRoles(String[] ids, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws WebException {
		try {
			roleService.deleteRoles(ids);
			source.loadResourceDefine();
			response.sendRedirect("queryroles.htm");
		} catch (Exception e) {
			JscnLogger.error("删除角色失败", e, this.getClass());
			throw new WebException("删除角色失败");
		}
		return null;
	}

	/**
	 * 根据编码查询角色操作员信息
	 * @param code 编码
	 * @param model 模型
	 * @return 返回
	 * @throws WebException 异常
	 * */
	@RequestMapping("tomodifyroleoperator")
	public String toModifyRoleOperator(String code, ModelMap model)
			throws WebException {
		try {
			List<OperatorEntity> operators = operatorRoleService
					.searchOperators(code);
			model.addAttribute("operators", operators);
			model.addAttribute("roleCode", code);
		} catch (Exception e) {
			JscnLogger.error("获得该角色的操作员失败", e, this.getClass());
			throw new WebException("获得该角色的操作员失败");
		}
		return "page/main/role/modifyroleoperator";
	}

	/**
	 * 根据编码查询角色操作员信息
	 * @param code 编码
	 * @param model 模型
	 * @return 返回
	 * @throws WebException 异常
	 * */
	@RequestMapping("getroleoperators")
	public @ResponseBody
	Map<String, Object> getRoleOperators(String code, ModelMap model)
			throws WebException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<OperatorEntity> operatorRoles = operatorRoleService
					.searchOperatorRoles(code);
			map.put("operatorRoles", operatorRoles);
		} catch (Exception e) {
			JscnLogger.error("查询角色操作员失败", e, this.getClass());
			throw new WebException("查询角色操作员失败");
		}
		return map;
	}

	/**
	 * 修改操作员角色信息
	 * @param id 操作者ID
	 * @param response 回应
	 * @param model 模型
	 * @return 返回
	 * @throws IOException 异常
	 * @throws WebException 异常
	 * */
	@RequestMapping("modifyroleoperator")
	public String modifyRoleOperator(String id, HttpServletResponse response,
			ModelMap model) throws IOException, WebException {
		try {
			roleService.modifyRoleOperator(id);
			response.sendRedirect("queryroles.htm");
		} catch (Exception e) {
			JscnLogger.error("修改角色操作员失败", e, this.getClass());
			throw new WebException("修改角色操作员失败");
		}
		return null;
	}

}
