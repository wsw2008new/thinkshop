package com.stone.shop.dao.mapper.bank;

import java.sql.SQLException;
import java.util.List;

import com.stone.shop.domain.bank.BankEntity;
import com.stone.shop.domain.persistent.QueryCondition;

public interface IBankMapper {

    /**
     * 获取支付银行信息
     *
     * @param condition 查询条件
     * @return 支付银行实体
     * @throws SQLException SQLException
     */
    public BankEntity getBank(QueryCondition condition) throws SQLException;

    /**
     * 添加支付银行
     *
     * @param entity 支付银行实体
     * @throws SQLException SQLException
     */
    public void insertBank(BankEntity entity) throws SQLException;
    
    /**
     * 更新支付银行
     * @param entity 支付银行实体
     * @throws SQLException SQLException
     */
    public void updateBank(BankEntity entity) throws SQLException;
    
    /**
     * 删除支付银行
     * @param id 支付银行ID
     * @throws SQLException SQLException
     */
    public void deleteBank(String id) throws SQLException;
    /**
     * 获取支付银行数量
     *
     * @param condition 查询条件
     * @return 支付银行数量
     * @throws SQLException SQLException
     */
    public Long countBanks(QueryCondition condition) throws SQLException;

    /**
     * 查询支付银行列表
     *
     * @param condition 查询条件
     * @return 支付银行实体列表
     * @throws SQLException SQLException
     */
    public List<BankEntity> queryBanks(QueryCondition condition) throws SQLException;
   
    /**
     * 查询信用卡或借记卡的支付方式
     * @param condition 查询条件
     * @return 支付银行实体
     * @throws SQLException
     */
    public List<BankEntity> getBanksByPayType(QueryCondition condition) throws SQLException;
}
