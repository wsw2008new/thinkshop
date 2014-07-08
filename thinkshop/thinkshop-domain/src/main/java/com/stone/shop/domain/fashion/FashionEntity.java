package com.stone.shop.domain.fashion;

import java.io.Serializable;

/**
 * 爱上时尚
 * @author wangy
 *
 */
public class FashionEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;//主键
	private String insertDate;//插入时间
	private String title;//标题
	private String desc;//介绍
	private String flag;//标识 用来区分图片
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return this.id+"--"+this.title+"--"+this.desc+"--"+this.flag;
	}
	
}
