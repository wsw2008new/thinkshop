package com.stone.shop.domain.manage.role;

import java.io.Serializable;

/**
 * 操作员角色关系实体
 */
public class OperatorRoleEntity implements Serializable {

    private static final long serialVersionUID = -109081611384003651L;

    private String id;

    private String operatorId;

    private String roleCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

}
