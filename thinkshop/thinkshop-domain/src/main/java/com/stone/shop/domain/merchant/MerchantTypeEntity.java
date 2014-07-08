package com.stone.shop.domain.merchant;

import java.io.Serializable;

public class MerchantTypeEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4987340838386672518L;
	public static final String ID_COLUMN = "id";
	public static final String NAME_COLUMN = "name";
	public static final String IS_ACTIVE_COLUMN = "is_active";
	public static final String SHOW_PIC_COLUMN = "show_pic";
	private String id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 是否激活 默认是激活的1,0：未激活
	 */
	private int is_active;
	/**
	 * 对应显示的LOGO
	 */
	private String show_pic;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	public String getShow_pic() {
		return show_pic;
	}
	public void setShow_pic(String show_pic) {
		this.show_pic = show_pic;
	}
	
}
