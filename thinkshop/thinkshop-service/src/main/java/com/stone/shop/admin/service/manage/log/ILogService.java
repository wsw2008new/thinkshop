package com.stone.shop.admin.service.manage.log;

import com.stone.shop.admin.exception.ServiceException;
import com.stone.shop.base.common.page.Pagination;
import com.stone.shop.domain.manage.log.LogEntity;
import com.stone.shop.domain.persistent.ResultList;

public interface ILogService {

	public static final String SERVICE_NAME = "com/mission/shop/admin/service/manage/log/ILogService";

	public void addLog(LogEntity log) throws ServiceException;

	public LogEntity getLogById(String id) throws ServiceException;

	public ResultList<LogEntity> getLogs(String operatorId,
			String operatorName, String beginTime, String endTime,
			Pagination page) throws ServiceException;

}
