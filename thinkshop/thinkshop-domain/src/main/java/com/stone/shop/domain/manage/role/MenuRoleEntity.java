package com.stone.shop.domain.manage.role;

import java.io.Serializable;

/**
 * 菜单角色关系实体
 */
public class MenuRoleEntity implements Serializable {

    private static final long serialVersionUID = 5025762681366229444L;

    private String id;

    private String menuCode;

    private String roleCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }


}
