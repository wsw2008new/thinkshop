package com.stone.shop.domain.model.role;

import java.io.Serializable;

import com.stone.shop.domain.manage.role.MenuEntity;

public class MenuRoleModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2869083788228151411L;

	private MenuEntity menu;

	private String isCheck;

	public MenuRoleModel() {
		if (menu == null) {
			menu = new MenuEntity();
		}
	}


	public String getCode() {
		return menu.getCode();
	}


	public void setCode(String code) {
		menu.setCode(code);
	}


	public String getTitle() {
		return menu.getTitle();
	}


	public void setTitle(String title) {
		menu.setTitle(title);
	}


	public String getUrl() {
		return menu.getUrl();
	}


	public void setUrl(String url) {
		menu.setUrl(url);
	}


	public String getParentCode() {
		return menu.getParentCode();
	}


	public void setParentCode(String parentCode) {
		menu.setParentCode(parentCode);
	}


	public Integer getMenuLevel() {
		return menu.getMenuLevel();
	}


	public void setMenuLevel(Integer menuLevel) {
		menu.setMenuLevel(menuLevel);
	}


	public Integer getChildrenCount() {
		return menu.getChildrenCount();
	}


	public void setChildrenCount(Integer childrenCount) {
		menu.setChildrenCount(childrenCount);
	}


	public Integer getDisplayNum() {
		return menu.getDisplayNum();
	}


	public void setDisplayNum(Integer displayNum) {
		menu.setDisplayNum(displayNum);
	}


	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

}
