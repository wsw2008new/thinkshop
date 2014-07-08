package com.stone.shop.admin.dao.mapper.news;

import java.sql.SQLException;
import java.util.List;

import com.stone.shop.domain.news.NewsEntity;
import com.stone.shop.domain.persistent.QueryCondition;

public interface INewsMapper {
    /**
     * 获取新闻列表信息
     *
     * @param condition 查询条件
     * @return 新闻列表实体
     * @throws SQLException SQLException
     */
    public NewsEntity getNews(QueryCondition condition) throws SQLException;

    /**
     * 添加新闻列表
     *
     * @param entity 新闻列表实体
     * @throws SQLException SQLException
     */
    public void insertNews(NewsEntity entity) throws SQLException;
    /**
     * 更新新闻列表
     * @param entity 新闻列表实体
     * @throws SQLException SQLException
     */
    public void updateNews(NewsEntity entity) throws SQLException;
    /**
     * 删除新闻列表
     * @param id 新闻列表ID
     * @throws SQLException SQLException
     */
    public void deleteNews(String id) throws SQLException;
    /**
     * 批量添加新闻列表
     *
     * @param entities 新闻列表实体列表
     * @throws SQLException SQLException
     */
    public void insertNews(List<NewsEntity> entities) throws SQLException;

    /**
     * 获取新闻列表数量
     *
     * @param condition 查询条件
     * @return 新闻列表数量
     * @throws SQLException SQLException
     */
    public Long countNews(QueryCondition condition) throws SQLException;
    /**
     * 查询新闻列表列表
     *
     * @param condition 查询条件
     * @return 新闻列表实体列表
     * @throws SQLException SQLException
     */
    public List<NewsEntity> queryNews(QueryCondition condition) throws SQLException;


}
