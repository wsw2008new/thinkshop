package com.stone.shop.dao.mapper.fashion;

import java.sql.SQLException;
import java.util.List;

import com.stone.shop.domain.fashion.FashionEntity;
import com.stone.shop.domain.persistent.QueryCondition;

public interface IFashionMapper {

    /**
     * 获取时尚图片信息
     *
     * @param condition 查询条件
     * @return 时尚图片实体
     * @throws SQLException SQLException
     */
    public FashionEntity getFashion(QueryCondition condition) throws SQLException;

    /**
     * 添加时尚图片
     *
     * @param entity 时尚图片实体
     * @throws SQLException SQLException
     */
    public void insertFashion(FashionEntity entity) throws SQLException;
    
    /**
     * 更新时尚图片
     * @param entity 时尚图片实体
     * @throws SQLException SQLException
     */
    public void updateFashion(FashionEntity entity) throws SQLException;
    
    /**
     * 删除时尚图片
     * @param id 时尚图片ID
     * @throws SQLException SQLException
     */
    public void deleteFashion(String id) throws SQLException;
    /**
     * 获取时尚图片数量
     *
     * @param condition 查询条件
     * @return 时尚图片数量
     * @throws SQLException SQLException
     */
    public Long countFashions(QueryCondition condition) throws SQLException;

    /**
     * 查询时尚图片列表
     *
     * @param condition 查询条件
     * @return 时尚图片实体列表
     * @throws SQLException SQLException
     */
    public List<FashionEntity> queryFashions(QueryCondition condition) throws SQLException;
   
    /**
     * 查询信用卡或借记卡的支付方式
     * @param condition 查询条件
     * @return 时尚图片实体
     * @throws SQLException
     */
    public List<FashionEntity> getFashionsByPayType(QueryCondition condition) throws SQLException;
}
