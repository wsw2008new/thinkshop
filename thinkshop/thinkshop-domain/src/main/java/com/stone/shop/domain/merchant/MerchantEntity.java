package com.stone.shop.domain.merchant;

import java.io.Serializable;
import java.util.Date;

import com.stone.shop.common.utils.DateTimeUtils;

public class MerchantEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5695455914026178781L;
	public static final String ID_COLUMN = "id";
	public static final String TYPE_ID_COLUMN = "merchant_type_id";
	public static final String TYPE_NAME_COLUMN = "merchant_type_name";
	public static final String NAME_COLUMN = "name";
	public static final String ADDRESS_COLUMN = "address";
	public static final String LOGO_COLUMN = "logo";
	public static final String IS_LOCK_COLUMN = "is_lock";
	public static final String FEATURE_COLUMN = "feature";
	public static final String PER_FEE_COLUMN = "per_fee";
	public static final String OPEN_TIME_COLUMN = "open_time";
	public static final String SERVICE_COLUMN = "service";
	public static final String ORDER_PHONE_COLUMN = "order_phone";
	public static final String INTRODUCE_COLUMN = "introduce";
	public static final String POP_PIC1_COLUMN = "pop_pic1";
	public static final String POP_PIC2_COLUMN = "pop_pic2";
	public static final String POP_PIC3_COLUMN = "pop_pic3";
	public static final String POP_PIC4_COLUMN = "pop_pic4";
	public static final String CREATE_TIME_COLUMN = "create_time";
	public static final String IS_JOIN_COLUMN = "is_join";
	private String id;
	/**
	 * 商户类型ID
	 */
	private String merchant_type_id;
	/**
	 * 商户类型名称
	 */
	private String merchant_type_name;
	/**
	 * 商户名称
	 */
	private String name;
	/**
	 * 商户地址
	 */
	private String address;
	/**
	 * 商户LOGO
	 */
	private String logo;
	/**
	 * 是否锁定 默认不锁定 0 ,1:锁定
	 */
	private int is_lock;
	/**
	 * 特色
	 */
	private String feature;
	/**
	 * 每人消费
	 */
	private String per_fee;
	/**
	 * 营业时间
	 */
	private String open_time;
	/**
	 * 创建时间（入驻时间）
	 */
	private String create_time;
	/**
	 * 是否加盟（是否入驻） 默认入驻1,0为入驻
	 */
	private int is_join;
	/**
	 * 服务配套 
	 */
	private String service;
	/**
	 * 预定热线
	 */
	private String order_phone;
	/**
	 * 商家介绍
	 */
	private String introduce;
	/**
	 * 展示图片
	 */
	private String pop_pic1;
	private String pop_pic2;
	private String pop_pic3;
	private String pop_pic4;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMerchant_type_id() {
		return merchant_type_id;
	}
	public void setMerchant_type_id(String merchant_type_id) {
		this.merchant_type_id = merchant_type_id;
	}
	
	public String getMerchant_type_name() {
		return merchant_type_name;
	}
	public void setMerchant_type_name(String merchant_type_name) {
		this.merchant_type_name = merchant_type_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public int getIs_lock() {
		return is_lock;
	}
	public void setIs_lock(int is_lock) {
		this.is_lock = is_lock;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getPer_fee() {
		return per_fee;
	}
	public void setPer_fee(String per_fee) {
		this.per_fee = per_fee;
	}
	public String getOpen_time() {
		return open_time;
	}
	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getOrder_phone() {
		return order_phone;
	}
	public void setOrder_phone(String order_phone) {
		this.order_phone = order_phone;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getPop_pic1() {
		return pop_pic1;
	}
	public void setPop_pic1(String pop_pic1) {
		this.pop_pic1 = pop_pic1;
	}
	public String getPop_pic2() {
		return pop_pic2;
	}
	public void setPop_pic2(String pop_pic2) {
		this.pop_pic2 = pop_pic2;
	}
	public String getPop_pic3() {
		return pop_pic3;
	}
	public void setPop_pic3(String pop_pic3) {
		this.pop_pic3 = pop_pic3;
	}
	public String getPop_pic4() {
		return pop_pic4;
	}
	public void setPop_pic4(String pop_pic4) {
		this.pop_pic4 = pop_pic4;
	}
	public String getCreate_time() {
		return DateTimeUtils.formateDate2Str(new Date(), "yyyy-mm-dd");
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public int getIs_join() {
		return is_join;
	}
	public void setIs_join(int is_join) {
		this.is_join = is_join;
	}
	
}
