package com.stone.shop.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Flow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -346509459169257885L;

	private Flow(Integer id, String value) {
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
	
	public static final Flow expenses =new Flow(1, "支出");
	
	public static final Flow income =new Flow(2, "收入");
	
	public static String getFlowValue(Integer id) {
		switch (id) {
		case 1:
			return expenses.getValue();
		case 2:
			return income.getValue();
		default:
			return "";
		}

	}
	public static List<Flow> getAllFlow(){
		List<Flow> list = new ArrayList<Flow>();
		list.add(expenses);
		list.add(income);
		return list;
	}
	
}
