package com.stone.shop.admin.dao.mapper.merchant;

import java.sql.SQLException;
import java.util.List;

import com.stone.shop.domain.merchant.CouponEntity;
import com.stone.shop.domain.persistent.QueryCondition;

public interface ICouponMapper {
    /**
     * 获取优惠券信息
     *
     * @param condition 查询条件
     * @return 优惠券实体
     * @throws SQLException SQLException
     */
    public CouponEntity getCoupon(QueryCondition condition) throws SQLException;

    /**
     * 添加优惠券
     *
     * @param entity 优惠券实体
     * @throws SQLException SQLException
     */
    public void insertCoupon(CouponEntity entity) throws SQLException;
    /**
     * 更新优惠券
     * @param entity 优惠券实体
     * @throws SQLException SQLException
     */
    public void updateCoupon(CouponEntity entity) throws SQLException;
    /**
     * 删除优惠券
     * @param id 优惠券ID
     * @throws SQLException SQLException
     */
    public void deleteCoupon(String id) throws SQLException;
    /**
     * 批量添加优惠券
     *
     * @param entities 优惠券实体列表
     * @throws SQLException SQLException
     */
    public void insertCoupon(List<CouponEntity> entities) throws SQLException;

    /**
     * 获取优惠券数量
     *
     * @param condition 查询条件
     * @return 优惠券数量
     * @throws SQLException SQLException
     */
    public Long countCoupon(QueryCondition condition) throws SQLException;
    /**
     * 查询优惠券列表
     *
     * @param condition 查询条件
     * @return 优惠券实体列表
     * @throws SQLException SQLException
     */
    public List<CouponEntity> queryCoupon(QueryCondition condition) throws SQLException;


}
