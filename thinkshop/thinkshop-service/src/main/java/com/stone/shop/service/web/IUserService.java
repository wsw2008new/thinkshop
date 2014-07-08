package com.stone.shop.service.web;

import java.util.Set;

import com.stone.shop.common.page.Pagination;
import com.stone.shop.domain.persistent.ResultList;
import com.stone.shop.domain.web.UserEntity;
import com.stone.shop.exception.ServiceException;

public interface IUserService {

	public static final String SERVICE_NAME = "com/jscn/pay/service/web/IUserService";

	/**
	 * 查询前端用户
	 * 
	 * @param status
	 *            用户状态
	 * @param sex
	 *            性别
	 * @param show_name
	 *            前端用户昵称名称
	 * @param page
	 *            分页对象
	 * @param column 显示列结合
	 * @return 前端用户实体列表
	 * @throws ServiceException
	 *             ServiceException
	 */
	public ResultList<UserEntity> queryUser(String status, String sex,String show_name, Pagination page, Set<String> column)
			throws ServiceException;

	/**
	 * 获取前端用户信息
	 * 
	 * @param id
	 *            前端用户实体ID
	 * @param answer_id 问题id
	 * @param answer_result 答案
	 * @param email 邮箱地址
	 * @return 前端用户实体
	 * @throws ServiceException
	 *             ServiceException
	 */
	public UserEntity getUser(String id,String email,String answer_id,String answer_result)
			throws ServiceException;
	/**
	 * 根据用户账号获取前端用户信息
	 * 
	 * @param email
	 *            前端用户实体email
	 * @return 前端用户实体
	 * @throws ServiceException
	 *             ServiceException
	 */
	public UserEntity getUserByEmail(String email)
			throws ServiceException;

	/**
	 * 添加前端用户
	 * 
	 * @param entity
	 *            前端用户实体
	 * @throws ServiceException
	 *             ServiceException
	 */
	public void addUser(UserEntity entity) throws ServiceException;

	/**
	 * 更新前端用户
	 * 
	 * @param entity
	 *            前端用户实体
	 * @throws ServiceException
	 *             ServiceException
	 */
	public void updateUser(UserEntity entity) throws ServiceException;

	/**
	 * 删除前端用户
	 * 
	 * @param id
	 *            前端用户实体ID
	 * @throws ServiceException
	 *             ServiceException
	 */
	public void deleteUser(String id) throws ServiceException;
	/**
	 * 激活前端用户
	 * 
	 * @param id
	 *            前端用户实体email
	 * @throws ServiceException
	 *             ServiceException
	 */
	public void activeUser(String email) throws ServiceException;
	/**
	 * 用户注册
	 * @param entity 前端用户实体
	 * @return 是否注册成功
	 * @throws ServiceException ServiceException
	 */
	public void regestUser(UserEntity entity) throws ServiceException;
	/**
	 * 用户登录
	 * @param email 邮箱
	 * @param password 密码
	 * @param code 验证码
	 * @return 是否登录成功
	 * @throws ServiceException ServiceException
	 */
	public Long loginUser(String email,String password,String code) throws ServiceException;
}
