package com.stone.shop.service.manage.role.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stone.shop.common.utils.StringUtils;
import com.stone.shop.dao.mapper.manage.role.IMenuRoleMapper;
import com.stone.shop.domain.manage.role.MenuEntity;
import com.stone.shop.domain.manage.role.MenuRoleEntity;
import com.stone.shop.domain.model.role.MenuRoleModel;
import com.stone.shop.domain.persistent.ResultList;
import com.stone.shop.domain.persistent.ResultListImpl;
import com.stone.shop.exception.ServiceException;
import com.stone.shop.service.manage.role.IMenuRoleService;
import com.stone.shop.utils.log.JscnLogger;

@Service(IMenuRoleService.SERVICE_NAME)
public class MenuRoleServiceImpl implements IMenuRoleService {

	@Autowired
	private IMenuRoleMapper menuRoleMapper;

	@Override
	@Transactional(readOnly = true)
	public ResultList<MenuEntity> queryUserMenus(String operatorId,
			String parentCode) throws ServiceException {
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("operatorId", operatorId);
			condition.put("parentCode", parentCode);
			List<MenuEntity> menus = menuRoleMapper.queryUserMenus(condition);
			ResultList<MenuEntity> result = new ResultListImpl<MenuEntity>();
			result.setResults(menus);
			return result;
		} catch (SQLException e) {
			JscnLogger.error("获取用户菜单错误", e, MenuRoleServiceImpl.class);
			throw new ServiceException("获取用户菜单错误", e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ResultList<MenuRoleModel> queryCheckMenus(String roleId,
			String parentCode) throws ServiceException {
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("roleCode", roleId);
			condition.put("parentCode", parentCode);
			List<MenuRoleModel> list = menuRoleMapper
					.queryCheckMenus(condition);
			ResultList<MenuRoleModel> result = new ResultListImpl<MenuRoleModel>();
			result.setResults(list);
			return result;
		} catch (SQLException e) {
			JscnLogger.error("获取菜单错误", e, MenuRoleServiceImpl.class);
			throw new ServiceException("获取菜单错误", e);
		}

	}

	@Override
	@Transactional
	public void addMenuToRole(String roleCode, String[] menuIds)
			throws ServiceException {
		try {
			List<MenuRoleEntity> menuroles = new ArrayList<MenuRoleEntity>();
			menuRoleMapper.deleteMenuRoles(new String[]{roleCode});
			if (menuIds != null) {
				for (int i = 0; i < menuIds.length; i++) {
					MenuRoleEntity menurole = new MenuRoleEntity();
					menurole.setId(StringUtils.getUUID());
					menurole.setRoleCode(roleCode);
					menurole.setMenuCode(menuIds[i]);
					menuroles.add(menurole);

				}
				menuRoleMapper.insertMenuRoles(menuroles);
			}
		} catch (Exception e) {
			JscnLogger.error("添加权限菜单错误", e, MenuRoleServiceImpl.class);
			throw new ServiceException("添加权限菜单错误", e);
		}

	}

	@Override
	public List<MenuEntity> queryMenuRoles(String roleCode)
			throws ServiceException {
		try {
			return menuRoleMapper.queryMenuRoles(roleCode);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
