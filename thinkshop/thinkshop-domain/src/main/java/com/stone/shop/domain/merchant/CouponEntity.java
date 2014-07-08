package com.stone.shop.domain.merchant;

import java.io.Serializable;

public class CouponEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3504563991785941036L;
	public static final String ID_COLUMN = "id";
	public static final String MERCHANT_ID_COLUMN = "merchant_id";
	public static final String MERCHANT_NAME_COLUMN = "merchant_name";
	public static final String NAME_COLUMN = "name";
	public static final String END_TIME_COLUMN = "end_time";
	public static final String USE_ADDRESS_COLUMN = "use_address";
	public static final String INTRODUCE_COLUMN = "introduce";
	public static final String IS_RECOMMEND_COLUMN = "is_recommend";
	public static final String SHOW_PIC_COLUMN = "show_pic";
	private String id;
	/**
	 * 商户ID
	 */
	private String merchant_id;
	/**
	 * 商户名称
	 */
	private String merchant_name;
	/**
	 * 优惠券名称
	 */
	private String name;
	/**
	 * 优惠截止日期
	 */
	private String end_time;
	/**
	 * 使用地址
	 */
	private String use_address;
	/**
	 * 优惠券详细介绍
	 */
	private String introduce;
	/**
	 * 是否是推荐的  0默认不是 1:是推荐的
	 */
	private int is_recommend;
	/**
	 * 显示的图片
	 */
	private String show_pic;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getMerchant_name() {
		return merchant_name;
	}
	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getUse_address() {
		return use_address;
	}
	public void setUse_address(String use_address) {
		this.use_address = use_address;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getIs_recommend() {
		return is_recommend;
	}
	public void setIs_recommend(int is_recommend) {
		this.is_recommend = is_recommend;
	}
	public String getShow_pic() {
		return show_pic;
	}
	public void setShow_pic(String show_pic) {
		this.show_pic = show_pic;
	}
	
}
