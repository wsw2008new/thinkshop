package com.stone.shop.domain.manage.role;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 角色实体
 */
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 8810537412502817265L;

    @NotEmpty(message = "角色不能为空")
    @Length(max = 20, message = "角色编码长度不超过20位")
    private String code;

    @NotEmpty(message = "角色名称不能为空")
    @Length(max = 50, message = "角色名称长度不超过50位")
    private String title;

    @Length(max = 100, message = "备注长度不超过100位")
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
