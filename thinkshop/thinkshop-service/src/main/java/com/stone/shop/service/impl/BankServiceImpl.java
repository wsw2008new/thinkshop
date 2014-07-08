package com.stone.shop.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.shop.common.page.Pagination;
import com.stone.shop.common.utils.StringUtils;
import com.stone.shop.dao.mapper.bank.IBankMapper;
import com.stone.shop.domain.bank.BankEntity;
import com.stone.shop.domain.persistent.QueryCondition;
import com.stone.shop.domain.persistent.ResultList;
import com.stone.shop.domain.persistent.ResultListImpl;
import com.stone.shop.exception.ServiceException;
import com.stone.shop.service.IBankService;
import com.stone.shop.utils.log.JscnLogger;

@Service(value = IBankService.SERVICE_NAME)
public class BankServiceImpl implements IBankService {

	@Autowired
	private IBankMapper bankMapper;

	@Override
	public BankEntity getBank(String id) throws ServiceException {
		try {
			QueryCondition condition = new QueryCondition();
			condition.addAllColumn(BankEntity.class);
			condition.addCondition("id", id);
			return bankMapper.getBank(condition);
		} catch (Exception e) {
			JscnLogger.error("获得支付银行信息错误", e, this.getClass());
			throw new ServiceException("获得支付银行信息错误", e);
		}
	}

	@Override
	public void addBank(BankEntity entity) throws ServiceException {
		try {
			bankMapper.insertBank(entity);
		} catch (Exception e) {
			JscnLogger.error("新增支付银行信息错误", e, this.getClass());
			throw new ServiceException("新增支付银行信息错误", e);
		}
	}

	@Override
	public void updateBank(BankEntity entity) throws ServiceException {
		try {
			bankMapper.updateBank(entity);
		} catch (Exception e) {
			JscnLogger.error("更新支付银行信息错误", e, this.getClass());
			throw new ServiceException("更新支付银行信息错误", e);
		}
	}

	@Override
	public void deleteBank(String id) throws ServiceException {
		try {
			bankMapper.deleteBank(id);
		} catch (Exception e) {
			JscnLogger.error("删除支付银行信息错误", e, this.getClass());
			throw new ServiceException("删除支付银行信息错误", e);
		}
	}

	@Override
	public ResultList<BankEntity> queryBanks(String id, String code,
			String name, Integer payType, Pagination page, Set<String> columns)
			throws ServiceException {
		try {
			QueryCondition condition = new QueryCondition();
			if (columns != null && columns.size() > 0) {
				condition.setColumns(columns);
			} else {
				condition.addAllColumn(BankEntity.class);
			}
			if (StringUtils.isNotEmpty(code)) {
				condition.addCondition("code", code);
			}
			if (StringUtils.isNotEmpty(name)) {
				condition.addCondition("name", name);
			}
			if (null != payType) {
				condition.addCondition("payType", payType);
			}
			if (page != null) {
				Long totalCount = bankMapper.countBanks(condition);
				page.setTotalCount(totalCount.intValue());
				condition.addCondition("minnum", page.getStartNo());
				condition.addCondition("maxnum", page.getEndNo());
			}
			List<BankEntity> list = bankMapper.queryBanks(condition);
			ResultList<BankEntity> res = new ResultListImpl<BankEntity>();
			res.setResults(list);
			res.setPage(page);
			return res;
		} catch (Exception e) {
			JscnLogger.error("查询支付银行信息错误", e, this.getClass());
			throw new ServiceException("查询支付银行信息错误", e);
		}
	}

	@Override
	public boolean checkExistCode(String code, String payType)
			throws ServiceException {
		try {
			QueryCondition condition = new QueryCondition();
			if (StringUtils.isNotEmpty(code)) {
				condition.addCondition("code", code);
			}
			if (StringUtils.isNotEmpty(payType)) {
				condition.addCondition("payType", payType);
			}
			Long count = bankMapper.countBanks(condition);
			if (count > 0) {
				return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			JscnLogger.error("判断银行编码是否存在信息错误", e, this.getClass());
			throw new ServiceException("判断银行编码是否存在信息错误", e);
		}

	}

	@Override
	public ResultList<BankEntity> getBanksByPayType(String isDebit,
			String isCredit,String model) throws ServiceException {
		try {
			QueryCondition condition = new QueryCondition();
			condition.addCondition("isDebit", isDebit);
			condition.addCondition("isCredit", isCredit);
			condition.addCondition("model", model);
			List<BankEntity> list = bankMapper.getBanksByPayType(condition);
			ResultList<BankEntity> res = new ResultListImpl<BankEntity>();
			res.setResults(list);
			return res;
		} catch (Exception e) {
			JscnLogger.error("查询是否支持信用卡或借记卡信息错误", e, this.getClass());
			throw new ServiceException("查询是否支持信用卡或借记卡信息错误", e);
		}
	}

}
