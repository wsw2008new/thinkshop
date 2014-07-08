package com.stone.shop.admin.service.model;

import java.util.ArrayList;
import java.util.List;


public class BooleanModel {
	private BooleanModel(Integer id, String value) {
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

	public static BooleanModel news1 = new BooleanModel(1, "0");
	public static BooleanModel news2 = new BooleanModel(2, "1");

	public static String getModelValue(Integer id) {
		switch (id) {
		case 1:
			return news1.getValue();
		case 2:
			return news2.getValue();
		default:
			return "其他";
		}
	}
	public static List<BooleanModel> getAllType(){
		List<BooleanModel> list = new ArrayList<BooleanModel>();
		list.add(news1);
		list.add(news2);
		return list;
	}
}
