package com.stone.shop.service;

import java.util.Set;

import com.stone.shop.common.page.Pagination;
import com.stone.shop.domain.news.NewsEntity;
import com.stone.shop.domain.persistent.ResultList;
import com.stone.shop.exception.ServiceException;

public interface INewsService {

	public static final String SERVICE_NAME = "com/jscn/pay/service/INewsService";

	/**
	 * 查询新闻列表
	 * 
	 * @param title
	 *            新闻标题
	 * @param newsType
	 *            新闻类型
	 * @param page
	 *            分页对象
	 * @param column 显示列结合
	 * @return 新闻列表实体列表
	 * @throws ServiceException
	 *             ServiceException
	 */
	public ResultList<NewsEntity> queryNews(String title, String newsType, Pagination page, Set<String> column)
			throws ServiceException;

	/**
	 * 获取新闻列表信息
	 * 
	 * @param id
	 *            新闻列表实体ID
	 * @return 新闻列表实体
	 * @throws ServiceException
	 *             ServiceException
	 */
	public NewsEntity getNews(String id)
			throws ServiceException;

	/**
	 * 添加新闻列表
	 * 
	 * @param entity
	 *            新闻列表实体
	 * @throws ServiceException
	 *             ServiceException
	 */
	public void addNews(NewsEntity entity) throws ServiceException;

	/**
	 * 更新新闻列表
	 * 
	 * @param entity
	 *            新闻列表实体
	 * @throws ServiceException
	 *             ServiceException
	 */
	public void updateNews(NewsEntity entity) throws ServiceException;

	/**
	 * 删除新闻列表
	 * 
	 * @param id
	 *            新闻列表实体ID
	 * @throws ServiceException
	 *             ServiceException
	 */
	public void deleteNews(String id) throws ServiceException;
}
