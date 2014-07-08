package com.stone.shop.domain.manage.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PayType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1448255120521357965L;

	private PayType(Integer id, String value) {
		this.id = id;
		this.value = value;
	}

	private Integer id;

	private String value;

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public static PayType bill99 = new PayType(1, "快钱支付");

	//public static PayType alipay = new PayType(2, "支付宝");
	
	//public static PayType tenpay = new PayType(3, "财付通");
	
	//public static PayType cunpay = new PayType(4, "银联支付");
	
	public static PayType tvpay = new PayType(5, "全媒体支付");
	
	//public static PayType other = new PayType(0, "其他");


	public static String getPayTypeValue(Integer id) {
		switch (id) {
		//case 0:
			//return other.getValue();
		case 1:
			return bill99.getValue();
		//case 2:
			//return alipay.getValue();
		//case 3:
			//return tenpay.getValue();
		//case 4:
			//return cunpay.getValue();
		case 5:
			return tvpay.getValue();
		default:
			return "其他";
			//return other.getValue();
		}

	}
	public static List<PayType> getAllPayType(){
		List<PayType> list = new ArrayList<PayType>();
		list.add(bill99);
		//list.add(other);
		//list.add(tenpay);
		//list.add(alipay);
		//list.add(cunpay);
		list.add(tvpay);
		return list;
	}
}
