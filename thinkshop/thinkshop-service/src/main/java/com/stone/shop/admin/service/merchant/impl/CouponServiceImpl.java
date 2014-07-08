package com.stone.shop.admin.service.merchant.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.shop.admin.dao.mapper.merchant.ICouponMapper;
import com.stone.shop.admin.exception.ServiceException;
import com.stone.shop.admin.service.merchant.ICouponService;
import com.stone.shop.admin.utils.log.JscnLogger;
import com.stone.shop.base.common.page.Pagination;
import com.stone.shop.base.common.utils.StringUtils;
import com.stone.shop.domain.merchant.CouponEntity;
import com.stone.shop.domain.persistent.QueryCondition;
import com.stone.shop.domain.persistent.ResultList;
import com.stone.shop.domain.persistent.ResultListImpl;

@Service(value = ICouponService.SERVICE_NAME)
public class CouponServiceImpl implements ICouponService {

	@Autowired
	private ICouponMapper couponMapper;

	@Override
	public CouponEntity getCoupon(String id) throws ServiceException {
		try {
			QueryCondition condition = new QueryCondition();
			condition.addAllColumn(CouponEntity.class);
			condition.addCondition("id", id);
			return couponMapper.getCoupon(condition);
		} catch (Exception e) {
			JscnLogger.error("获得优惠券信息错误", e, this.getClass());
			throw new ServiceException("获得优惠券信息错误", e);
		}
	}

	@Override
	public void addCoupon(CouponEntity entity) throws ServiceException {
		try {
			couponMapper.insertCoupon(entity);
		} catch (Exception e) {
			JscnLogger.error("新增优惠券信息错误", e, this.getClass());
			throw new ServiceException("新增优惠券信息错误", e);
		}
	}

	@Override
	public void updateCoupon(CouponEntity entity) throws ServiceException {
		try {
			couponMapper.updateCoupon(entity);
		} catch (Exception e) {
			JscnLogger.error("更新优惠券信息错误", e, this.getClass());
			throw new ServiceException("更新优惠券信息错误", e);
		}
	}

	@Override
	public void deleteCoupon(String id) throws ServiceException {
		try {
			couponMapper.deleteCoupon(id);
		} catch (Exception e) {
			JscnLogger.error("删除优惠券信息错误", e, this.getClass());
			throw new ServiceException("删除优惠券信息错误", e);
		}
	}

	@Override
	public ResultList<CouponEntity> queryCoupon(String is_recommend, String merchant_name, Pagination page, Set<String> columns)
			throws ServiceException {
		try {
			QueryCondition condition = new QueryCondition();
			if (columns != null && columns.size() > 0) {
				condition.setColumns(columns);
			} else {
				condition.addAllColumn(CouponEntity.class);
			}
			if (StringUtils.isNotEmpty(is_recommend)) {
				condition.addCondition("is_recommend", is_recommend);
			}
			if (StringUtils.isNotEmpty(merchant_name)) {
				condition.addCondition("merchant_name", merchant_name);
			}
			if (page != null) {
				Long totalCount = couponMapper.countCoupon(condition);
				page.setTotalCount(totalCount.intValue());
				condition.addCondition("minnum", page.getStartNo());
				condition.addCondition("maxnum", page.getEndNo());
			}
			List<CouponEntity> list = couponMapper.queryCoupon(condition);
			ResultList<CouponEntity> res = new ResultListImpl<CouponEntity>();
			res.setResults(list);
			res.setPage(page);
			return res;
		} catch (Exception e) {
			JscnLogger.error("查询优惠券信息错误", e, this.getClass());
			throw new ServiceException("查询优惠券信息错误", e);
		}
	}
}
