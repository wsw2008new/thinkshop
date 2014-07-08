package com.stone.shop.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SettState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1969684271950903740L;

	private SettState(Integer id, String value) {
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

	public static SettState no_settlt = new SettState(0, "未结算");

	public static SettState settlt = new SettState(1, "已结算");

	public static String getStateValue(Integer id) {
		switch (id) {
			case 0 :
				return no_settlt.getValue();
			case 1 :
				return settlt.getValue();
			default :
				return "";
		}

	}

	public static List<SettState> getAllState() {
		List<SettState> list = new ArrayList<SettState>();
		list.add(no_settlt);
		list.add(settlt);
		return list;
	}

}
