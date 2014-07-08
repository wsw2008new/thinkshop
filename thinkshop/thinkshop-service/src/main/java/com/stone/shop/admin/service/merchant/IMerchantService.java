package com.stone.shop.admin.service.merchant;

import java.util.Set;

import com.stone.shop.admin.exception.ServiceException;
import com.stone.shop.base.common.page.Pagination;
import com.stone.shop.domain.merchant.MerchantEntity;
import com.stone.shop.domain.persistent.ResultList;

public interface IMerchantService {

	public static final String SERVICE_NAME = "com/jscn/pay/service/merchant/IMerchantService";

	/**
	 * 查询商户
	 * 
	 * @param is_lock
	 *            是否锁定
	 * @param merchant_type_name
	 *            商户类型名称
	 * @param name
	 *            商户名称
	 * @param page
	 *            分页对象
	 * @param column 显示列结合
	 * @return 商户实体列表
	 * @throws ServiceException
	 *             ServiceException
	 */
	public ResultList<MerchantEntity> queryMerchant(String is_lock, String merchant_type_name,String name, Pagination page, Set<String> column)
			throws ServiceException;

	/**
	 * 获取商户信息
	 * 
	 * @param id
	 *            商户实体ID
	 * @return 商户实体
	 * @throws ServiceException
	 *             ServiceException
	 */
	public MerchantEntity getMerchant(String id)
			throws ServiceException;

	/**
	 * 添加商户
	 * 
	 * @param entity
	 *            商户实体
	 * @throws ServiceException
	 *             ServiceException
	 */
	public void addMerchant(MerchantEntity entity) throws ServiceException;

	/**
	 * 更新商户
	 * 
	 * @param entity
	 *            商户实体
	 * @throws ServiceException
	 *             ServiceException
	 */
	public void updateMerchant(MerchantEntity entity) throws ServiceException;

	/**
	 * 删除商户
	 * 
	 * @param id
	 *            商户实体ID
	 * @throws ServiceException
	 *             ServiceException
	 */
	public void deleteMerchant(String id) throws ServiceException;
}
