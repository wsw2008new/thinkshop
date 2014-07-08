package com.stone.shop.admin.web.controller.role;

import java.io.IOException;
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

import com.stone.shop.admin.web.controller.BaseController;
import com.stone.shop.common.page.Pagination;
import com.stone.shop.common.utils.StringUtils;
import com.stone.shop.domain.manage.role.MenuEntity;
import com.stone.shop.domain.persistent.ResultList;
import com.stone.shop.service.manage.role.IMenuService;
import com.stone.shop.utils.exception.WebException;
import com.stone.shop.utils.log.JscnLogger;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

	@Autowired
	private IMenuService menuService;

	/**
	 * 根据父节点查询子菜单
	 * @param parentCode 父节点
	 * @param model 模型
	 * @return 返回值
	 * @throws WebException 异常
	 * */
	@RequestMapping("querymenus")
	public String queryMenus(String parentCode, ModelMap model) throws WebException {
		try {
			if (StringUtils.isEmpty(parentCode)) {
				parentCode = "0";
			}
			ResultList<MenuEntity> res = menuService.queryMenus(parentCode,
					null);
			model.addAttribute("menus", res);
			model.addAttribute("parentCode", parentCode);
			return "page/main/menu/menulist";
		} catch (Exception e) {
			JscnLogger.error("加载数据错误", e, this.getClass());
			throw new WebException("加载数据错误，请稍后重试...");
		}
	}
															
	/**
	 * 根据父节点查询子菜单
	 * @param parentCode 父节点
	 * @param model 模型
	 * @return 返回值
	 * @throws WebException 异常
	 * */
	@RequestMapping("querychildrenmenus")
	public String queryChildrenMenus(String parentCode, ModelMap model)
			throws WebException {
		try {
			ResultList<MenuEntity> res = menuService.queryMenus(parentCode,
					null);
			model.addAttribute("menus", res);
			return "page/main/menu/childmenu";
		} catch (Exception e) {
			JscnLogger.error("加载数据错误", e, this.getClass());
			throw new WebException("加载数据错误，请稍后重试...");
		}
	}

	/**
	 * 根据父节点加载子菜单
	 * @param parentCode 父节点
	 * @param model 模型
	 * @return 返回值
	 * @throws WebException 异常
	 * */
	@RequestMapping("loadchildrenmenus")
	public String loadChildrenMenus(String parentCode, ModelMap model)
			throws WebException {
		try {
			ResultList<MenuEntity> res = menuService.queryMenus(parentCode,
					null);
			model.addAttribute("menus", res);
			model.addAttribute("parentCode", parentCode);
			return "page/main/menu/menutable";
		} catch (Exception e) {
			JscnLogger.error("加载数据错误", e, this.getClass());
			throw new WebException("加载数据错误，请稍后重试...");
		}
	}

	/**
	 * 根据父节点加载子菜单
	 * @param code 节点
	 * @param page 分页
	 * @param model 模型
	 * @return 返回值
	 * @throws WebException 异常
	 * */
	@RequestMapping("getmenu")
	public String getMenu(String code, Pagination page, ModelMap model)
			throws WebException {
		try {

			if (!code.equals("0")) {
				MenuEntity menu = menuService.getMenu(code);
				model.addAttribute("menu", menu);
				model.addAttribute("ok", 1);
			} else {
				model.addAttribute("ok", 2);
			}
			return "page/main/menu/addmenu";
		} catch (Exception e) {
			JscnLogger.error("获取菜单错误", e, this.getClass());
			throw new WebException("获取菜单错误，请稍后重试...");
		}
	}

	/**
	 * 添加菜单信息
	 * @param menu 菜单
	 * @param result 结果
	 * @param response 回应
	 * @param model 模型
	 * @return 返回值
	 * @throws IOException IO异常
	 * @throws WebException Web异常
	 */
	@RequestMapping("addmenu")
	public @ResponseBody
	Map<String, Object> addMenu(@Valid MenuEntity menu, BindingResult result,
			HttpServletResponse response, ModelMap model) throws IOException,
			WebException {
		try {
			getValidError(result);
		} catch (SystemException e) {
			JscnLogger.error("添加菜单错误", e, this.getClass());
			throw new WebException("添加菜单错误：", e);
		}
		try {
			if (menu != null) {
				menuService.addMenu(menu);
			}
			Integer parentCount=null;
			if(!menu.getParentCode().equals("0")){
				parentCount = menuService.getMenu(menu.getParentCode())
					.getChildrenCount();
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("menu", menu);
			map.put("parentCount", parentCount);
			return map;
		} catch (Exception e) {
			JscnLogger.error("添加菜单错误", e, this.getClass());
			throw new WebException("添加菜单错误，请稍后重试...");
		}

	}

	/**
	 * 根据菜单编码查询菜单是否存在
	 * @param code 菜单编码
	 * @return 返回值
	 * @throws WebException Web异常
	 */
	@RequestMapping("ismenuexist")
	public @ResponseBody
	Map<String, String> isMenuExist(String code) throws WebException {
		Map<String, String> map = new HashMap<String, String>();
		MenuEntity menu = menuService.getMenu(code);
		if (menu != null) {
			map.put("message", "1");
		} else {
			map.put("message", "0");
		}
		return map;

	}

	/**
	 * 根据菜单编码查询菜单信息
	 * @param code 菜单编码
	 * @param model 模型
	 * @return 返回值
	 */
	@RequestMapping("tomodifymenu")
	public String toModifyMenu(String code, ModelMap model) {

		MenuEntity menu = menuService.getMenu(code);

		if (menu != null) {
			model.addAttribute("menu", menu);
			MenuEntity parent = menuService.getMenu(menu.getParentCode());
			model.addAttribute("parent", parent);

		}
		return "page/main/menu/modifymenu";
	}

	/**
	 * 修改菜单信息
	 * @param menu 菜单
	 * @param result 结果
	 * @param request 请求
	 * @param response 回应
	 * @param model 模型
	 * @return 返回值
	 * @throws IOException IO异常
	 * @throws WebException Web异常
	 */
	@RequestMapping("modifymenu")
	public @ResponseBody
	Map<String, Object> modifyMenu(@Valid MenuEntity menu,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws WebException,
			IOException {
		try {
			getValidError(result);
		} catch (SystemException e) {
			JscnLogger.error("修改菜单错误", e, this.getClass());
			throw new WebException("修改菜单错误：", e);
		}
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (menu != null) {
				menuService.modifyMenu(menu);
				Integer parentCount=null;
				if(!menu.getParentCode().equals("0")){
					parentCount = menuService.getMenu(menu.getParentCode())
						.getChildrenCount();
				}
				map.put("menu", menu);
				map.put("parentCount", parentCount);
			}
			return map;
		} catch (Exception e) {
			JscnLogger.error("修改菜单错误", e, this.getClass());
			throw new WebException("修改菜单错误，请稍后重试...");
		}

	}

	/**
	 * 删除菜单信息
	 * @param codes 菜单
	 * @param parentCode 父菜单
	 * @param request 请求
	 * @param response 回应
	 * @param model 模型
	 * @return 返回值
	 * @throws WebException Web异常
	 */
	@RequestMapping("deletemenu")
	public @ResponseBody
	Map<String, Object> deletemenu(String codes, String parentCode,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws WebException {
		try {

			Map<String, Object> map = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(codes)
					&& StringUtils.isNotEmpty(parentCode)) {
				String[] codeArray = codes.split("[,]");
				menuService.deleteMenus(codeArray, parentCode);
				map.put("codes", codeArray);
				return map;
			} else {
				return null;
			}
		} catch (Exception e) {
			JscnLogger.error("删除菜单错误", e, this.getClass());
			throw new WebException("删除菜单错误，请稍后重试...");
		}
	}
}
