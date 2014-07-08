package com.stone.shop.admin.service.manage.role.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stone.shop.admin.dao.mapper.manage.role.IOperatorRoleMapper;
import com.stone.shop.admin.exception.ServiceException;
import com.stone.shop.admin.service.manage.role.IOperatorRoleService;
import com.stone.shop.admin.utils.log.JscnLogger;
import com.stone.shop.domain.manage.role.OperatorEntity;
import com.stone.shop.domain.manage.role.OperatorRoleEntity;

@Service(value = IOperatorRoleService.SERVICE_NAME)

public class OperatorRoleServiceImpl implements IOperatorRoleService {
	
	@Autowired
	private IOperatorRoleMapper operatorRoleMapper;
	
	
	@Override
	@Transactional(readOnly=true)
	public List<OperatorEntity> searchOperatorRoles(String roleCode)
			throws ServiceException {
		List<OperatorEntity> operatorRoles=null;
		try {
			operatorRoles = operatorRoleMapper.searchOperatorRoles(roleCode);
		} catch (SQLException e) {
			JscnLogger.error("查询该角色的操作员信息失败", e,this.getClass());
			throw new ServiceException("查询该角色的操作员信息失败",e);
		}
		return operatorRoles;
	}
	@Override
	@Transactional
	public void addOperatorRole(OperatorRoleEntity operatorRole)
			throws ServiceException {
		try {
			operatorRoleMapper.insertOperatorRole(new OperatorRoleEntity[]{operatorRole});
		} catch (SQLException e) {
			JscnLogger.error("添加该角色的操作员信息失败", e,this.getClass());
			throw new ServiceException("添加该角色的操作员信息失败",e);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<OperatorEntity> searchOperators(String roleCode)
			throws ServiceException {
		List<OperatorEntity> operatorRoles=null;
		try {
			operatorRoles = operatorRoleMapper.searchOperators(roleCode);
		} catch (SQLException e) {
			JscnLogger.error("查询该角色的操作员信息失败", e,this.getClass());
			throw new ServiceException("查询该角色的操作员信息失败",e);
		}
		return operatorRoles;
	}

}
