package com.stone.shop.service.merchant;

import java.util.Set;

import com.stone.shop.common.page.Pagination;
import com.stone.shop.domain.merchant.MerchantTypeEntity;
import com.stone.shop.domain.persistent.ResultList;
import com.stone.shop.exception.ServiceException;

public interface IMerchantTypeService {

	public static final String SERVICE_NAME = "com/jscn/pay/service/merchant/IMerchantTypeService";

	/**
	 * 查询商户类型
	 * 
	 * @param page
	 *            分页对象
	 * @param column 显示列结合
	 * @return 商户类型实体列表
	 * @throws ServiceException
	 *             ServiceException
	 */
	public ResultList<MerchantTypeEntity> queryMerchantType(String is_active,Pagination page, Set<String> column)
			throws ServiceException;

	/**
	 * 获取商户类型信息
	 * 
	 * @param id
	 *            商户类型实体ID
	 * @return 商户类型实体
	 * @throws ServiceException
	 *             ServiceException
	 */
	public MerchantTypeEntity getMerchantType(String id)
			throws ServiceException;

	/**
	 * 添加商户类型
	 * 
	 * @param entity
	 *            商户类型实体
	 * @throws ServiceException
	 *             ServiceException
	 */
	public void addMerchantType(MerchantTypeEntity entity) throws ServiceException;

	/**
	 * 更新商户类型
	 * 
	 * @param entity
	 *            商户类型实体
	 * @throws ServiceException
	 *             ServiceException
	 */
	public void updateMerchantType(MerchantTypeEntity entity) throws ServiceException;

	/**
	 * 删除商户类型
	 * 
	 * @param id
	 *            商户类型实体ID
	 * @throws ServiceException
	 *             ServiceException
	 */
	public void deleteMerchantType(String id) throws ServiceException;
}
