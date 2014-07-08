package com.stone.shop.service;

import java.util.Set;

import com.stone.shop.common.page.Pagination;
import com.stone.shop.domain.bank.BankEntity;
import com.stone.shop.domain.persistent.ResultList;
import com.stone.shop.exception.ServiceException;

public interface IBankService {

	public static final String SERVICE_NAME = "com/jscn/pay/service/IBankService";

	/**
	 * 查询支付银行
	 * 
	 * @param id
	 *            支付银行ID
	 * @param code
	 *            支付银行编码
	 * @param name
	 *            支付银行名称
	 * @param payType
	 *            支付类型
	 * @param page
	 *            分页对象
	 * @param column 显示列结合
	 * @return 支付银行实体列表
	 * @throws ServiceException
	 *             ServiceException
	 */
	public ResultList<BankEntity> queryBanks(String id, String code,
			String name, Integer payType, Pagination page, Set<String> column)
			throws ServiceException;

	/**
	 * 获取支付银行信息
	 * 
	 * @param id
	 *            支付银行实体ID
	 * @return 支付银行实体
	 * @throws ServiceException
	 *             ServiceException
	 */
	public BankEntity getBank(String id)
			throws ServiceException;

	/**
	 * 添加支付银行
	 * 
	 * @param entity
	 *            支付银行实体
	 * @throws ServiceException
	 *             ServiceException
	 */
	public void addBank(BankEntity entity) throws ServiceException;

	/**
	 * 更新支付银行
	 * 
	 * @param entity
	 *            支付银行实体
	 * @throws ServiceException
	 *             ServiceException
	 */
	public void updateBank(BankEntity entity) throws ServiceException;

	/**
	 * 删除支付银行
	 * 
	 * @param id
	 *            支付银行实体ID
	 * @throws ServiceException
	 *             ServiceException
	 */
	public void deleteBank(String id) throws ServiceException;

	/**
	 * 判断银行编码是否存在
	 * 
	 * @param code 银行编码
	 * @param payType 支付方式
	 * @return 是否存在银行编码 true：1 false：0
	 * @throws ServiceException
	 */
	public boolean checkExistCode(String code,String payType) throws ServiceException;
    /**
     * 
     * @param isDebit 是否是借记卡
     * @param isCredit 是否是信用卡
     * @param model 机顶盒号
     * @return 支付银行实体
     * @throws ServiceException ServiceException
     */
    public ResultList<BankEntity> getBanksByPayType(String isDebit,String isCredit,String model) throws ServiceException;
}
