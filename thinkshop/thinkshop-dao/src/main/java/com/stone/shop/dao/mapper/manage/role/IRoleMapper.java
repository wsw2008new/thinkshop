package com.stone.shop.dao.mapper.manage.role;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.stone.shop.domain.manage.role.RoleEntity;

public interface IRoleMapper {


    public Long roleCount(Map<String, Object> condition) throws SQLException;

    public List<RoleEntity> searchRoles(Map<String, Object> condition) throws SQLException;

    public RoleEntity getRole(String code) throws SQLException;

    public void updateRole(RoleEntity role) throws SQLException;

    public void insertRole(RoleEntity role) throws SQLException;

    public void deleteRoles(String[] codes) throws SQLException;


}
