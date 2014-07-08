package com.stone.shop.admin.service.manage.role;

import com.stone.shop.admin.exception.ServiceException;
import com.stone.shop.base.common.page.Pagination;
import com.stone.shop.domain.manage.role.RoleEntity;
import com.stone.shop.domain.persistent.ResultList;

public interface IRoleService {

	public static final String SERVICE_NAME = "com/jscn/pay/service/manage/role/IRoleService";

	/**
	 * 根据条件查询角色信息
	 * **/
	public ResultList<RoleEntity> queryRoles(String title, Pagination page)
			throws ServiceException;

	/**
	 * 根据ID查询角色信息
	 * **/

	public RoleEntity getRole(String code) throws ServiceException;

	/**
	 * 修改角色信息
	 * */

	public void modifyRole(RoleEntity role) throws ServiceException;

	/**
	 * 添加角色信息
	 * */
	public void addRole(RoleEntity role) throws ServiceException;

	/**
	 * 删除所选信息
	 * */
	public void deleteRoles(String[] ids) throws ServiceException;

	/**
	 * 添加操作员角色信息
	 * */
	public void modifyRoleOperator(String id) throws ServiceException;

}
