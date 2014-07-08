package com.stone.shop.admin.dao.mapper.merchant;

import java.sql.SQLException;
import java.util.List;

import com.stone.shop.domain.merchant.MerchantTypeEntity;
import com.stone.shop.domain.persistent.QueryCondition;

public interface IMerchantTypeMapper {
    /**
     * 获取商户类型信息
     *
     * @param condition 查询条件
     * @return 商户类型实体
     * @throws SQLException SQLException
     */
    public MerchantTypeEntity getMerchantType(QueryCondition condition) throws SQLException;

    /**
     * 添加商户类型
     *
     * @param entity 商户类型实体
     * @throws SQLException SQLException
     */
    public void insertMerchantType(MerchantTypeEntity entity) throws SQLException;
    /**
     * 更新商户类型
     * @param entity 商户类型实体
     * @throws SQLException SQLException
     */
    public void updateMerchantType(MerchantTypeEntity entity) throws SQLException;
    /**
     * 删除商户类型
     * @param id 商户类型ID
     * @throws SQLException SQLException
     */
    public void deleteMerchantType(String id) throws SQLException;
    /**
     * 批量添加商户类型
     *
     * @param entities 商户类型实体列表
     * @throws SQLException SQLException
     */
    public void insertMerchantType(List<MerchantTypeEntity> entities) throws SQLException;

    /**
     * 获取商户类型数量
     *
     * @param condition 查询条件
     * @return 商户类型数量
     * @throws SQLException SQLException
     */
    public Long countMerchantType(QueryCondition condition) throws SQLException;
    /**
     * 查询商户类型列表
     *
     * @param condition 查询条件
     * @return 商户类型实体列表
     * @throws SQLException SQLException
     */
    public List<MerchantTypeEntity> queryMerchantType(QueryCondition condition) throws SQLException;


}
