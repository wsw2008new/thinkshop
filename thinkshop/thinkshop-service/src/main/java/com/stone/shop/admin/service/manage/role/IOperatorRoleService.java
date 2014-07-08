package com.stone.shop.admin.service.manage.role;

import java.util.List;

import com.stone.shop.admin.exception.ServiceException;
import com.stone.shop.domain.manage.role.OperatorEntity;
import com.stone.shop.domain.manage.role.OperatorRoleEntity;

public interface IOperatorRoleService {

	public static final String SERVICE_NAME = "com/jscn/pay/service/manage/role/IOperatorRoleService";
	
	/*
	 * 获取操作员角色信息
	 * **/
	
	public List<OperatorEntity> searchOperatorRoles(String roleCode) throws ServiceException;
	
	/*
	 * 添加操作员角色信息
	 * **/
	public void addOperatorRole(OperatorRoleEntity operatorRole)throws ServiceException;
	
	/*
	 * 获取操作员角色信息
	 * **/
	
	public List<OperatorEntity> searchOperators(String roleCode) throws ServiceException;
	
}
