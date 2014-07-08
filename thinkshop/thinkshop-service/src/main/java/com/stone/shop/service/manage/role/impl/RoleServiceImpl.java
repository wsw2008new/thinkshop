package com.stone.shop.service.manage.role.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stone.shop.common.page.Pagination;
import com.stone.shop.common.utils.StringUtils;
import com.stone.shop.dao.mapper.manage.role.IMenuRoleMapper;
import com.stone.shop.dao.mapper.manage.role.IOperatorRoleMapper;
import com.stone.shop.dao.mapper.manage.role.IRoleMapper;
import com.stone.shop.domain.manage.role.OperatorRoleEntity;
import com.stone.shop.domain.manage.role.RoleEntity;
import com.stone.shop.domain.persistent.ResultList;
import com.stone.shop.domain.persistent.ResultListImpl;
import com.stone.shop.exception.ServiceException;
import com.stone.shop.service.manage.role.IRoleService;
import com.stone.shop.utils.log.JscnLogger;

@Service(value = IRoleService.SERVICE_NAME)
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleMapper roleMapper;

	@Autowired
	private IMenuRoleMapper menuRoleMapper;

	@Autowired
	private IOperatorRoleMapper operatorRoleMapper;

	@Override
	@Transactional(readOnly = true)
	public ResultList<RoleEntity> queryRoles(String title, Pagination page)
			throws ServiceException {
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			ResultList<RoleEntity> res = new ResultListImpl<RoleEntity>();
			if (StringUtils.isNotEmpty(title)) {
				condition.put("title", title);
			}
			if (page != null) {
				Long totalCount;
				totalCount = roleMapper.roleCount(condition);
				page.setTotalCount(totalCount.intValue());

				condition.put("minnum", page.getStartNo());
				condition.put("maxnum", page.getEndNo());
			}
			List<RoleEntity> list = roleMapper.searchRoles(condition);
			res.setPage(page);
			res.setResults(list);
			return res;
		} catch (SQLException e) {
			JscnLogger.error("查询角色失败", e, this.getClass());
			throw new ServiceException("查询角色失败", e);
		}

	}
	@Override
	@Transactional(readOnly = true)
	public RoleEntity getRole(String code) throws ServiceException {
		RoleEntity role = null;
		try {
			role = roleMapper.getRole(code);
		} catch (SQLException e) {
			JscnLogger.error("获得角色失败", e, this.getClass());
			throw new ServiceException("获得信息失败", e);
		}
		return role;
	}

	@Override
	@Transactional
	public void modifyRole(RoleEntity role) throws ServiceException {
		try {
			roleMapper.updateRole(role);
		} catch (SQLException e) {
			JscnLogger.error("修改角色信息失败", e, this.getClass());
			throw new ServiceException("修改角色信息失败", e);
		}
	}

	@Override
	@Transactional
	public void addRole(RoleEntity role) throws ServiceException {
		try {
			roleMapper.insertRole(role);
		} catch (SQLException e) {
			JscnLogger.error("添加角色失败", e, this.getClass());
			throw new ServiceException("添加角色失败", e);
		}
	}

	@Override
	@Transactional
	public void deleteRoles(String[] ids) throws ServiceException {
		try {
			roleMapper.deleteRoles(ids);
			menuRoleMapper.deleteMenuRoles(ids);
			operatorRoleMapper.deleteOperatorRole(ids);
		} catch (SQLException e) {
			JscnLogger.error("删除角色失败", e, this.getClass());
			throw new ServiceException("删除角色失败", e);
		}

	}

	@Override
	@Transactional
	public void modifyRoleOperator(String id) throws ServiceException {
		try {
			if (id.contains(",")) {
				String[] operatorIds = id.split(",");
				String roleCode = operatorIds[operatorIds.length - 1];
				operatorRoleMapper.deleteOperatorRole(new String[]{roleCode});
				OperatorRoleEntity[] operatorRoles = new OperatorRoleEntity[operatorIds.length - 1];
				for (int i = 0; i < operatorIds.length - 1; i++) {
					String operatorId = operatorIds[i];
					OperatorRoleEntity operatorRole = new OperatorRoleEntity();
					operatorRole.setOperatorId(operatorId);
					operatorRole.setRoleCode(roleCode);
					operatorRole.setId(StringUtils.getUUID());
					operatorRoles[i] = operatorRole;
				}

				operatorRoleMapper.insertOperatorRole(operatorRoles);
			} else {
				operatorRoleMapper.deleteOperatorRole(new String[]{id});
			}
		} catch (SQLException e) {
			JscnLogger.error("修改该角色的操作员信息失败", e, this.getClass());
			throw new ServiceException("修改该角色的操作员信息失败", e);
		}
	}

}
