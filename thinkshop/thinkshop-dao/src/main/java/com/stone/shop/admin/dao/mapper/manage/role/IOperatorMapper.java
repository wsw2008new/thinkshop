package com.stone.shop.admin.dao.mapper.manage.role;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.stone.shop.domain.manage.role.OperatorEntity;

public interface IOperatorMapper {

	public OperatorEntity getOperator(String operatorId) throws SQLException;

	public void insertOperator(OperatorEntity operator) throws SQLException;

	public void updateOperator(OperatorEntity operator) throws SQLException;

	public void deleteOperators(String[] operatorIds) throws SQLException;

	public Long operatorCount(Map<String, Object> condition) throws SQLException;  
	
	public List<OperatorEntity> queryOperators(Map<String, Object> condition)
			throws SQLException;

}
