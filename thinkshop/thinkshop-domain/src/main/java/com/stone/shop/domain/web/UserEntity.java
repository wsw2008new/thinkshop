package com.stone.shop.domain.web;

import java.io.Serializable;

public class UserEntity implements Serializable{
	private static final long serialVersionUID = -1696406941785634159L;
	
	public static final String ID_COLUMN = "id";
	public static final String EMAIL_COLUMN = "email";
	public static final String SHOW_NAME_COLUMN = "show_name";
	public static final String MOBILE_COLUMN = "mobile";
	public static final String ID_CARD_COLUMN = "id_card";
	public static final String STATUS_COLUMN = "status";
	public static final String PRELOGIN_TIME_COLUMN = "prelogin_time";
	public static final String HEAD_PHOTO_COLUMN = "head_photo";
	public static final String AGE_COLUMN = "age";
	public static final String ZODIAC_COLUMN = "zodiac";
	public static final String CONSTELLATION_COLUMN = "constellation";
	public static final String SIGNATURE_COLUMN = "signature";
	public static final String ADDRESS_COLUMN = "address";
	private String id;
	private String email;
	/**
	 * 昵称
	 */
	private String show_name;
	/**
	 * 默认是2:保密 0表示女 1：标识男
	 */
	private int sex;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 生日
	 */
	private String birthday;
	/**
	 * 头像
	 */
	private String head_photo;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 年龄
	 */
	private int age;
	/**
	 * 生肖
	 */
	private String zodiac;
	/**
	 * 星座
	 */
	private String constellation;
	/**
	 * 个性签名
	 */
	private String signature;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 问题ID
	 */
	private String answer_id;
	/**
	 * 设置的答案
	 */
	private String answer_result;
	/**
	 * 身份证
	 */
	private String id_card;
	/**
	 * 用户状态 
	 * 0:正常用户，1：锁定用户， 2：封号，3：锁三天
	 */
	private int status;
	/**
	 * 上一次登录时间
	 */
	private String prelogin_time;
	/**
	 * 注册时间
	 */
	private String regest_time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getShow_name() {
		return show_name;
	}
	public void setShow_name(String show_name) {
		this.show_name = show_name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getHead_photo() {
		return head_photo;
	}
	public void setHead_photo(String head_photo) {
		this.head_photo = head_photo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getZodiac() {
		return zodiac;
	}
	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAnswer_id() {
		return answer_id;
	}
	public void setAnswer_id(String answer_id) {
		this.answer_id = answer_id;
	}
	public String getAnswer_result() {
		return answer_result;
	}
	public void setAnswer_result(String answer_result) {
		this.answer_result = answer_result;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	public String getPrelogin_time() {
		return prelogin_time;
	}
	public void setPrelogin_time(String prelogin_time) {
		this.prelogin_time = prelogin_time;
	}
	public String getRegest_time() {
		return regest_time;
	}
	public void setRegest_time(String regest_time) {
		this.regest_time = regest_time;
	}
	
}
