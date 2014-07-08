package com.stone.shop.admin.service.model;

import java.util.ArrayList;
import java.util.List;


public class NewsModel {
	private NewsModel(Integer id, String value) {
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

	public static NewsModel news1 = new NewsModel(1, "新沂要闻");
	public static NewsModel news2 = new NewsModel(2, "区县部门动态");
	public static NewsModel news3 = new NewsModel(3, "国内要闻");
	public static NewsModel news4 = new NewsModel(4, "国际要闻");

	public static String getModelValue(Integer id) {
		switch (id) {
		case 1:
			return news1.getValue();
		case 2:
			return news2.getValue();
		case 3:
			return news3.getValue();
		case 4:
			return news4.getValue();
		default:
			return "其他";
		}
	}
	public static List<NewsModel> getAllNewsType(){
		List<NewsModel> list = new ArrayList<NewsModel>();
		list.add(news1);
		list.add(news2);
		list.add(news3);
		list.add(news4);
		return list;
	}
}
