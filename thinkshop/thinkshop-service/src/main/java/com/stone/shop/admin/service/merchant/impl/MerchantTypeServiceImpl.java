package com.stone.shop.admin.service.merchant.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.shop.admin.dao.mapper.merchant.IMerchantTypeMapper;
import com.stone.shop.admin.exception.ServiceException;
import com.stone.shop.admin.service.merchant.IMerchantTypeService;
import com.stone.shop.admin.utils.log.JscnLogger;
import com.stone.shop.base.common.page.Pagination;
import com.stone.shop.base.common.utils.StringUtils;
import com.stone.shop.domain.merchant.MerchantTypeEntity;
import com.stone.shop.domain.persistent.QueryCondition;
import com.stone.shop.domain.persistent.ResultList;
import com.stone.shop.domain.persistent.ResultListImpl;

@Service(value = IMerchantTypeService.SERVICE_NAME)
public class MerchantTypeServiceImpl implements IMerchantTypeService {

	@Autowired
	private IMerchantTypeMapper merchantTypeMapper;
	@Override
	public MerchantTypeEntity getMerchantType(String id) throws ServiceException {
		try {
			QueryCondition condition = new QueryCondition();
			condition.addAllColumn(MerchantTypeEntity.class);
			condition.addCondition("id", id);
			return merchantTypeMapper.getMerchantType(condition);
		} catch (Exception e) {
			JscnLogger.error("获得商户类型信息错误", e, this.getClass());
			throw new ServiceException("获得商户类型信息错误", e);
		}
	}

	@Override
	public void addMerchantType(MerchantTypeEntity entity) throws ServiceException {
		try {
			merchantTypeMapper.insertMerchantType(entity);
		} catch (Exception e) {
			JscnLogger.error("新增商户类型信息错误", e, this.getClass());
			throw new ServiceException("新增商户类型信息错误", e);
		}
	}

	@Override
	public void updateMerchantType(MerchantTypeEntity entity) throws ServiceException {
		try {
			merchantTypeMapper.updateMerchantType(entity);
		} catch (Exception e) {
			JscnLogger.error("更新商户类型信息错误", e, this.getClass());
			throw new ServiceException("更新商户类型信息错误", e);
		}
	}

	@Override
	public void deleteMerchantType(String id) throws ServiceException {
		try {
			merchantTypeMapper.deleteMerchantType(id);
		} catch (Exception e) {
			JscnLogger.error("删除商户类型信息错误", e, this.getClass());
			throw new ServiceException("删除商户类型信息错误", e);
		}
	}

	@Override
	public ResultList<MerchantTypeEntity> queryMerchantType(String is_active,Pagination page, Set<String> columns)
			throws ServiceException {
		try {
			QueryCondition condition = new QueryCondition();
			if (columns != null && columns.size() > 0) {
				condition.setColumns(columns);
			} else {
				condition.addAllColumn(MerchantTypeEntity.class);
			}
			if(StringUtils.isNotEmpty(is_active)){
				condition.addCondition("is_active", is_active);
			}
			if (page != null) {
				Long totalCount = merchantTypeMapper.countMerchantType(condition);
				page.setTotalCount(totalCount.intValue());
				condition.addCondition("minnum", page.getStartNo());
				condition.addCondition("maxnum", page.getEndNo());
			}
			List<MerchantTypeEntity> list = merchantTypeMapper.queryMerchantType(condition);
			ResultList<MerchantTypeEntity> res = new ResultListImpl<MerchantTypeEntity>();
			res.setResults(list);
			res.setPage(page);
			return res;
		} catch (Exception e) {
			JscnLogger.error("查询商户类型信息错误", e, this.getClass());
			throw new ServiceException("查询商户类型信息错误", e);
		}
	}
}
