package com.stone.shop.admin.service.web.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.shop.admin.dao.mapper.web.IUserMapper;
import com.stone.shop.admin.exception.ServiceException;
import com.stone.shop.admin.service.web.IUserService;
import com.stone.shop.admin.utils.log.JscnLogger;
import com.stone.shop.base.common.page.Pagination;
import com.stone.shop.base.common.utils.StringUtils;
import com.stone.shop.domain.persistent.QueryCondition;
import com.stone.shop.domain.persistent.ResultList;
import com.stone.shop.domain.persistent.ResultListImpl;
import com.stone.shop.domain.web.UserEntity;

@Service(value=IUserService.SERVICE_NAME)
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserMapper userMapper;
	@Override
	public ResultList<UserEntity> queryUser(String status, String sex,
			String show_name, Pagination page, Set<String> column)
			throws ServiceException {
		try {
			QueryCondition condition = new QueryCondition();
			if (column != null && column.size() > 0) {
				condition.setColumns(column);
			} else {
				condition.addAllColumn(UserEntity.class);
			}
			if (StringUtils.isNotEmpty(status)) {
				condition.addCondition("status", status);
			}
			if (StringUtils.isNotEmpty(sex)) {
				condition.addCondition("sex", sex);
			}
			if (StringUtils.isNotEmpty(show_name)) {
				condition.addCondition("show_name", show_name);
			}
			if (page != null) {
				Long totalCount = userMapper.countUser(condition);
				page.setTotalCount(totalCount.intValue());
				condition.addCondition("minnum", page.getStartNo());
				condition.addCondition("maxnum", page.getEndNo());
			}
			List<UserEntity> list = userMapper.queryUser(condition);
			ResultList<UserEntity> res = new ResultListImpl<UserEntity>();
			res.setResults(list);
			res.setPage(page);
			return res;
		} catch (Exception e) {
			JscnLogger.error("查询前端用户信息错误", e, this.getClass());
			throw new ServiceException("查询前端用户信息错误", e);
		}
	}

	@Override
	public UserEntity getUser(String id,String email,String answer_id,String answer_result) throws ServiceException {
		try {
			QueryCondition condition = new QueryCondition();
			condition.addAllColumn(UserEntity.class);
			if (StringUtils.isNotEmpty(id)) {
				condition.addCondition("id", id);
			}
			if(StringUtils.isNotEmpty(email)){
				condition.addCondition("email", email);
			}
			if (StringUtils.isNotEmpty(answer_id)) {
				condition.addCondition("answer_id", answer_id);
			}
			if (StringUtils.isNotEmpty(answer_result)) {
				condition.addCondition("answer_result", answer_result);
			}
			return userMapper.getUser(condition);
		} catch (Exception e) {
			JscnLogger.error("获得前端用户信息错误", e, this.getClass());
			throw new ServiceException("获得前端用户信息错误", e);
		}
	}
	@Override
	public UserEntity getUserByEmail(String email) throws ServiceException {
		try {
			QueryCondition condition = new QueryCondition();
			condition.addAllColumn(UserEntity.class);
			condition.addCondition("email", email);
			return userMapper.getUserByEmail(condition);
		} catch (Exception e) {
			JscnLogger.error("获得前端用户信息错误", e, this.getClass());
			throw new ServiceException("获得前端用户信息错误", e);
		}
	}

	@Override
	public void addUser(UserEntity entity) throws ServiceException {
		try {
			userMapper.insertUser(entity);
		} catch (Exception e) {
			JscnLogger.error("新增前端用户信息错误", e, this.getClass());
			throw new ServiceException("新增前端用户信息错误", e);
		}
	}

	@Override
	public void updateUser(UserEntity entity) throws ServiceException {
		try {
			userMapper.updateUser(entity);
		} catch (Exception e) {
			JscnLogger.error("更新前端用户信息错误", e, this.getClass());
			throw new ServiceException("更新前端用户信息错误", e);
		}
	}

	@Override
	public void deleteUser(String id) throws ServiceException {
		try {
			userMapper.deleteUser(id);
		} catch (Exception e) {
			JscnLogger.error("删除前端用户信息错误", e, this.getClass());
			throw new ServiceException("删除前端用户信息错误", e);
		}
	}

	@Override
	public void activeUser(String email) throws ServiceException {
		try {
			userMapper.activeUser(email);
		} catch (Exception e) {
			JscnLogger.error("激活前端用户信息错误", e, this.getClass());
			throw new ServiceException("激活前端用户信息错误", e);
		}
		
	}

	@Override
	public void regestUser(UserEntity entity) throws ServiceException {
		try {
			 userMapper.regestUser(entity);
		} catch (Exception e) {
			JscnLogger.error("注册前端用户信息错误", e, this.getClass());
			throw new ServiceException("注册前端用户信息错误", e);
		}
	}

	@Override
	public Long loginUser(String email, String password, String code)
			throws ServiceException {
		try {
			QueryCondition condition = new QueryCondition();
			condition.addAllColumn(UserEntity.class);
			condition.addCondition("email", email);
			condition.addCondition("password", password);
			return userMapper.loginUser(condition);
		} catch (Exception e) {
			JscnLogger.error("登录前端用户信息错误", e, this.getClass());
			throw new ServiceException("登录前端用户信息错误", e);
		}
	}

}
