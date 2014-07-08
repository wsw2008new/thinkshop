package com.stone.shop.admin.dao.mapper.web;

import java.sql.SQLException;
import java.util.List;

import com.stone.shop.domain.persistent.QueryCondition;
import com.stone.shop.domain.web.UserEntity;

public interface IUserMapper {

    /**
     * 获取前段用户信息
     *
     * @param condition 查询条件
     * @return 前段用户实体
     * @throws SQLException SQLException
     */
    public UserEntity getUser(QueryCondition condition) throws SQLException;
    /**
     * 根据账号获取前段用户信息
     *
     * @param condition 查询条件
     * @return 前段用户实体
     * @throws SQLException SQLException
     */
    public UserEntity getUserByEmail(QueryCondition condition) throws SQLException;

    /**
     * 添加前段用户
     *
     * @param entity 前段用户实体
     * @throws SQLException SQLException
     */
    public void insertUser(UserEntity entity) throws SQLException;
    
    /**
     * 更新前段用户
     * @param entity 前段用户实体
     * @throws SQLException SQLException
     */
    public void updateUser(UserEntity entity) throws SQLException;
    
    /**
     * 删除前端用户
     * @param id 前段用户ID
     * @throws SQLException SQLException
     */
    public void deleteUser(String id) throws SQLException;
    /**
     * 激活前段用户
     * @param id 前段用户email
     * @throws SQLException SQLException
     */
    public void activeUser(String email) throws SQLException;
    /**
     * 获取前段用户数量
     *
     * @param condition 查询条件
     * @return 前段用户数量
     * @throws SQLException SQLException
     */
    public Long countUser(QueryCondition condition) throws SQLException;

    /**
     * 查询前段用户列表
     *
     * @param condition 查询条件
     * @return 前段用户实体列表
     * @throws SQLException SQLException
     */
    public List<UserEntity> queryUser(QueryCondition condition) throws SQLException;
   
    /**
     * 用户注册
     * @param entity 前端用户实体
     * @return 注册是否成功
     * @throws SQLException SQLException
     */
    public void regestUser(UserEntity entity) throws SQLException;
    /**
     * 用户登录
     * @param condition 查询条件
     * @return 登录是否成功
     * @throws SQLException SQLException
     */
    public Long loginUser(QueryCondition condition) throws SQLException;
}
