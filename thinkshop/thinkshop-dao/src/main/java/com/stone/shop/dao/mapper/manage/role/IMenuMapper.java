package com.stone.shop.dao.mapper.manage.role;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.stone.shop.domain.manage.role.MenuEntity;

public interface IMenuMapper {

	
	public Long menuCount(Map<String, Object> condition) throws SQLException;  
	
	public List<MenuEntity> queryMenus(Map<String, Object> condition)
			throws SQLException;
	
	public MenuEntity getMenu(String code) throws SQLException;
	
	public void insertMenu(MenuEntity menu) throws SQLException;
	
	public void updateMenu(MenuEntity menu) throws SQLException;
	
	public void updateChildrenCount(Map<String, Object> condition) throws SQLException;
	
	public void deleteMenus(String[] codes) throws SQLException;
}
