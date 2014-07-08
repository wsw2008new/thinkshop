package com.stone.shop.dao.mapper.merchant;

import java.sql.SQLException;
import java.util.List;

import com.stone.shop.domain.merchant.MerchantEntity;
import com.stone.shop.domain.persistent.QueryCondition;

public interface IMerchantMapper {
    /**
     * 获取商户信息
     *
     * @param condition 查询条件
     * @return 商户实体
     * @throws SQLException SQLException
     */
    public MerchantEntity getMerchant(QueryCondition condition) throws SQLException;

    /**
     * 添加商户
     *
     * @param entity 商户实体
     * @throws SQLException SQLException
     */
    public void insertMerchant(MerchantEntity entity) throws SQLException;
    /**
     * 更新商户
     * @param entity 商户实体
     * @throws SQLException SQLException
     */
    public void updateMerchant(MerchantEntity entity) throws SQLException;
    /**
     * 删除商户
     * @param id 商户ID
     * @throws SQLException SQLException
     */
    public void deleteMerchant(String id) throws SQLException;
    /**
     * 批量添加商户
     *
     * @param entities 商户实体列表
     * @throws SQLException SQLException
     */
    public void insertMerchant(List<MerchantEntity> entities) throws SQLException;

    /**
     * 获取商户数量
     *
     * @param condition 查询条件
     * @return 商户数量
     * @throws SQLException SQLException
     */
    public Long countMerchant(QueryCondition condition) throws SQLException;
    /**
     * 查询商户列表
     *
     * @param condition 查询条件
     * @return 商户实体列表
     * @throws SQLException SQLException
     */
    public List<MerchantEntity> queryMerchant(QueryCondition condition) throws SQLException;


}
