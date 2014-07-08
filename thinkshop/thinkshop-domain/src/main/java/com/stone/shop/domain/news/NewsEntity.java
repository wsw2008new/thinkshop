package com.stone.shop.domain.news;

import java.io.Serializable;
import java.sql.Blob;

/**
 * 新闻实体类
 * @author wangyu
 *
 */
public class NewsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3382556289661442560L;

	public static final String ID_COLUMN = "id";
	public static final String CREATETIME_COLUMN = "createTime";
	public static final String HAPPENTIME_COLUMN = "happenTime";
	public static final String TITLE_COLUMN = "title";
	public static final String FROMWHERE_COLUMN = "fromWhere";
	public static final String NEWSTYPE_COLUMN = "newsType";
	public static final String REPORTER_COLUMN = "reporter";
	public static final String CONTENT_COLUMN = "content";
	public static final String ISACTIVE_COLUMN = "isactive";
	
	private String id;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 发生时间
	 */
	private String happenTime;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 新闻来源
	 */
	private String fromWhere;
	/**
	 * 新闻类型 
	 * 1新沂要闻 | 2区县\部门动态 | 3国内要闻 | 4国际要闻
	 */
	private String newsType;
	/**
	 * 报道者
	 */
	private String reporter;
	/**
	 * 内容
	 */
	private Blob content;
	/**
	 * 是否激活 1:激活 0：未激活
	 * @return
	 */
	public int isactive;

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getHappenTime() {
		return happenTime;
	}

	public void setHappenTime(String happenTime) {
		this.happenTime = happenTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFromWhere() {
		return fromWhere;
	}

	public void setFromWhere(String fromWhere) {
		this.fromWhere = fromWhere;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public Blob getContent() {
		return content;
	}

	public void setContent(Blob content) {
		this.content = content;
	}
}
