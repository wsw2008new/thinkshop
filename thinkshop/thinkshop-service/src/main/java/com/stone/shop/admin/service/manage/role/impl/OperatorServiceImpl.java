package com.stone.shop.admin.service.manage.role.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stone.shop.admin.dao.mapper.manage.role.IOperatorMapper;
import com.stone.shop.admin.dao.mapper.manage.role.IOperatorRoleMapper;
import com.stone.shop.admin.exception.ServiceException;
import com.stone.shop.admin.service.manage.role.IOperatorService;
import com.stone.shop.admin.utils.log.JscnLogger;
import com.stone.shop.base.common.exception.SystemException;
import com.stone.shop.base.common.page.Pagination;
import com.stone.shop.base.common.utils.EncryptUtil;
import com.stone.shop.base.common.utils.StringUtils;
import com.stone.shop.domain.manage.role.OperatorEntity;
import com.stone.shop.domain.manage.role.OperatorRoleEntity;
import com.stone.shop.domain.persistent.ResultList;
import com.stone.shop.domain.persistent.ResultListImpl;

@Service(value = IOperatorService.SERVICE_NAME)
public class OperatorServiceImpl implements IOperatorService {

	@Autowired
	private IOperatorMapper operatorMapper;

	@Autowired
	private IOperatorRoleMapper operatorRoleMapper;

	@Override
	@Transactional
	public void addOperator(OperatorEntity operator, String[] roleCodes)
			throws ServiceException {
		try {
			operatorMapper.insertOperator(operator);
			if (roleCodes != null) {
				List<OperatorRoleEntity> operatorRoles = new ArrayList<OperatorRoleEntity>();
				for (int i = 0; i < roleCodes.length; i++) {
					OperatorRoleEntity ore = new OperatorRoleEntity();
					ore.setId(StringUtils.getUUID());
					ore.setOperatorId(operator.getOperatorId());
					ore.setRoleCode(roleCodes[i]);
					operatorRoles.add(ore);
				}
				OperatorRoleEntity[] ores = operatorRoles
						.toArray(new OperatorRoleEntity[] {});
				operatorRoleMapper.insertOperatorRole(ores);
			}
		} catch (SQLException e) {
			JscnLogger.error("添加操作员错误", e, this.getClass());
			throw new ServiceException("添加操作员错误", e);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public ResultList<OperatorEntity> queryOperators(String operatorId,
			String name, String status, Pagination page)
			throws ServiceException {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("operatorId", operatorId);
		condition.put("name", name);
		condition.put("status", status);
		Long totalCount;
		ResultList<OperatorEntity> res = new ResultListImpl<OperatorEntity>();
		try {
			if (page != null) {
				totalCount = operatorMapper.operatorCount(condition);
				page.setTotalCount(totalCount.intValue());

				condition.put("minnum", page.getStartNo());
				condition.put("maxnum", page.getEndNo());

			}
			List<OperatorEntity> list;

			list = operatorMapper.queryOperators(condition);

			res.setPage(page);
			res.setResults(list);
		} catch (SQLException e) {
			JscnLogger.error("查询操作员错误", e, this.getClass());
			throw new ServiceException("查询操作员错误", e);
		}
		return res;

	}

	@Override
	@Transactional(readOnly = true)
	public OperatorEntity getOperatorById(String operatorId)
			throws ServiceException {
		OperatorEntity operator = null;
		try {
			operator = operatorMapper.getOperator(operatorId);
		} catch (SQLException e) {
			JscnLogger.error("获得操作员信息失败", e, this.getClass());
			throw new ServiceException("获得操作员信息失败", e);
		}
		return operator;
	}

	@Override
	@Transactional
	public void modifyOperator(OperatorEntity operator) throws ServiceException {
		try {
			if (operator != null) {
				operatorMapper.updateOperator(operator);
			}
		} catch (SQLException e) {
			JscnLogger.error("修改操作员信息失败", e, this.getClass());
			throw new ServiceException("修改操作员信息失败", e);
		}

	}

	@Override
	@Transactional
	public void modifyOperatorPwd(String operatorId, String password,
			String oldPassword) {
		try {
			OperatorEntity oldoperator = operatorMapper.getOperator(operatorId);
			if (oldoperator != null) {
				String oldPasswordMd5 = EncryptUtil.md5Hex(oldPassword);
				if (!oldPasswordMd5.equals(oldoperator.getPassword())) {
					throw new SystemException("原密码错误");
				}
				oldoperator.setPassword(EncryptUtil.md5Hex(password));
				operatorMapper.updateOperator(oldoperator);
			}
		} catch (SQLException e) {
			JscnLogger.error("修改操作员密码失败", e, this.getClass());
			throw new ServiceException("修改操作员密码失败", e);
		}

	}

	@Override
	@Transactional
	public void deleteOperator(String operatorId) throws ServiceException {
		try {
			if (operatorId != null) {
				deleteOperators(new String[] { operatorId });
			}
		} catch (Exception e) {
			JscnLogger.error("删除操作员信息失败", e, this.getClass());
			throw new ServiceException("删除操作员信息失败", e);
		}

	}

	@Override
	@Transactional
	public void deleteOperators(String[] ids) throws ServiceException {
		try {
			operatorMapper.deleteOperators(ids);
			operatorRoleMapper.deleteRoleByOperator(ids);
		} catch (SQLException e) {
			JscnLogger.error("删除操作员信息失败", e, this.getClass());
			throw new ServiceException("删除操作员信息失败", e);
		}
	}

}
