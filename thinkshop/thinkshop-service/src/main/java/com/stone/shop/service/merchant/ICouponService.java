package com.stone.shop.service.merchant;

import java.util.Set;

import com.stone.shop.common.page.Pagination;
import com.stone.shop.domain.merchant.CouponEntity;
import com.stone.shop.domain.persistent.ResultList;
import com.stone.shop.exception.ServiceException;

public interface ICouponService {

	public static final String SERVICE_NAME = "com/jscn/pay/service/merchant/ICouponService";

	/**
	 * 查询优惠券
	 * 
	 * @param is_recommend
	 *            是否是推荐
	 * @param merchant_name
	 *            商户名称
	 * @param page
	 *            分页对象
	 * @param column 显示列结合
	 * @return 优惠券实体列表
	 * @throws ServiceException
	 *             ServiceException
	 */
	public ResultList<CouponEntity> queryCoupon(String is_recommend, String merchant_name, Pagination page, Set<String> column)
			throws ServiceException;

	/**
	 * 获取优惠券信息
	 * 
	 * @param id
	 *            优惠券实体ID
	 * @return 优惠券实体
	 * @throws ServiceException
	 *             ServiceException
	 */
	public CouponEntity getCoupon(String id)
			throws ServiceException;

	/**
	 * 添加优惠券
	 * 
	 * @param entity
	 *            优惠券实体
	 * @throws ServiceException
	 *             ServiceException
	 */
	public void addCoupon(CouponEntity entity) throws ServiceException;

	/**
	 * 更新优惠券
	 * 
	 * @param entity
	 *            优惠券实体
	 * @throws ServiceException
	 *             ServiceException
	 */
	public void updateCoupon(CouponEntity entity) throws ServiceException;

	/**
	 * 删除优惠券
	 * 
	 * @param id
	 *            优惠券实体ID
	 * @throws ServiceException
	 *             ServiceException
	 */
	public void deleteCoupon(String id) throws ServiceException;
}
