package com.stone.shop.service.manage.log;

import com.stone.shop.common.page.Pagination;
import com.stone.shop.domain.manage.log.LogEntity;
import com.stone.shop.domain.persistent.ResultList;
import com.stone.shop.exception.ServiceException;

public interface ILogService {

	public static final String SERVICE_NAME = "com/mission/shop/admin/service/manage/log/ILogService";

	public void addLog(LogEntity log) throws ServiceException;

	public LogEntity getLogById(String id) throws ServiceException;

	public ResultList<LogEntity> getLogs(String operatorId,
			String operatorName, String beginTime, String endTime,
			Pagination page) throws ServiceException;

}
