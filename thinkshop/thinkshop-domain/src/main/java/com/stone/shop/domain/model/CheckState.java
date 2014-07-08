package com.stone.shop.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1969684271950903740L;

	private CheckState(Integer id, String value) {
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

	public boolean isEqual(Integer state) {
		return getId().intValue() == state.intValue();
	}
	
	public static CheckState notchecked = new CheckState(-1, "未通过");

	public static CheckState uncheck = new CheckState(0, "未审核");

	public static CheckState checked = new CheckState(1, "已审核");

	public static String getStateValue(Integer id) {
		switch (id) {
			case 0 :
				return uncheck.getValue();
			case 1 :
				return checked.getValue();
			case -1 :
				return notchecked.getValue();
			default :
				return "";
		}

	}

	public static List<CheckState> getAllState() {
		List<CheckState> list = new ArrayList<CheckState>();
		list.add(notchecked);
		list.add(uncheck);
		list.add(checked);
		return list;
	}

}
