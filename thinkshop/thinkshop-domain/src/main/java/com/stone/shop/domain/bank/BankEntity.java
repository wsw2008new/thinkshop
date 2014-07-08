package com.stone.shop.domain.bank;

import java.io.Serializable;

import com.stone.shop.domain.persistent.QueryCondition;

/**
 * 
 * @ClassName: BankEntity
 * @Description: TODO(系统支付银行)
 * @author wy
 * @date 2012-1-10 上午9:23:50
 * 
 */
public class BankEntity implements Serializable {

	private static final long serialVersionUID = 2830886798338599530L;

	public static final String id_column = "id";

	public static final String code_column = "code";

	public static final String name_column = "name";

	public static final String payType_column = "pay_type";

	public static final String rate_column = "rate";

	public static final String isDebit_column = "is_debit";

	public static final String isCredit_column = "is_credit";

	public static final String isEnterpise_column = "is_enterpise";

	public static final String logoPath_column = "logo_path";

	public static final String webSite_column = "web_site";

	public static final String remark_column = "remark";

	public static final String dispalyOrder_column = "dispaly_order";
	
	public static final String isActive_column = "is_active";
	/**
	 * ID
	 */
	private String id;
	/**
	 * 银行编码 VARCHAR(10) 非空
	 */
	private String code;
	/**
	 * 银行名称VARCHAR(20) 非空
	 */
	private String name;
	/**
	 * 所属支付方式(非空)
	 */
	private Integer payType;
	/**
	 * 手续费率
	 */
	private Float rate;
	/**
	 * 是否支持借记卡(非空)
	 */
	private Boolean isDebit;
	/**
	 * 是否支持信用卡(非空)
	 */
	private Boolean isCredit;
	/**
	 * 是否支持企业网银(非空)
	 */
	private Boolean isEnterpise;
	/**
	 * 银行Logo VARCHAR(200)
	 */
	private String logoPath;
	/**
	 * 银行网站VARCHAR(200)非空
	 */
	private String webSite;
	/**
	 * 描述 CLOB 非
	 */
	private String remark;
	/**
	 * 是否激活
	 */
	private Boolean isActive;
	/**
	 * 指定排序
	 */
	private Integer dispalyOrder;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public Boolean getIsDebit() {
		return isDebit;
	}

	public void setIsDebit(Boolean isDebit) {
		this.isDebit = isDebit;
	}

	public Boolean getIsCredit() {
		return isCredit;
	}

	public void setIsCredit(Boolean isCredit) {
		this.isCredit = isCredit;
	}

	public Boolean getIsEnterpise() {
		return isEnterpise;
	}

	public void setIsEnterpise(Boolean isEnterpise) {
		this.isEnterpise = isEnterpise;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getDispalyOrder() {
		return dispalyOrder;
	}

	public void setDispalyOrder(Integer dispalyOrder) {
		this.dispalyOrder = dispalyOrder;
	}
	

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public static void main(String[] args) {
		QueryCondition con=new QueryCondition();
		con.addAllColumn(BankEntity.class);
	}

}