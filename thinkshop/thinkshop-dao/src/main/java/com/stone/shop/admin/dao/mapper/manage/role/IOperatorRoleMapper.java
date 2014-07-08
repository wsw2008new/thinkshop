package com.stone.shop.admin.dao.mapper.manage.role;

import java.sql.SQLException;
import java.util.List;

import com.stone.shop.domain.manage.role.OperatorEntity;
import com.stone.shop.domain.manage.role.OperatorRoleEntity;

public interface IOperatorRoleMapper {
	
	public List<OperatorRoleEntity> queryOperatorRoles(String operatorId) throws SQLException;
	
	public List<OperatorEntity> searchOperatorRoles(String roleCode) throws SQLException;
	
	public void deleteOperatorRole(String[] roleCodes) throws SQLException;
	
	public void deleteRoleByOperator(String[] operatorIds) throws SQLException;
	
	public void insertOperatorRole(OperatorRoleEntity[] operatorRoles)throws SQLException;
	
	public List<OperatorEntity> searchOperators(String roleCode) throws SQLException;
}
