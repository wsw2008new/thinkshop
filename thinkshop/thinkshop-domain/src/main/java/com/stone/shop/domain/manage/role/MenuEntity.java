package com.stone.shop.domain.manage.role;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 菜单实体
 */
public class MenuEntity implements Serializable {

    private static final long serialVersionUID = -6757112965191151998L;

    @NotEmpty(message = "菜单编码不能为空")
    @Length(max = 15, message = "菜单编码长度不能超过15位")
    private String code;

    @NotEmpty(message = "菜单名称不能为空")
    @Length(max = 30, message = "菜单名称长度不能超过30位")
    private String title;

    @Length(max = 200, message = "菜单URL长度不能超过200位")
    private String url;

    private String parentCode;

    private Integer menuLevel;

    private Integer childrenCount;

    private Integer displayNum;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Integer getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(Integer childrenCount) {
        this.childrenCount = childrenCount;
    }

    public Integer getDisplayNum() {
        return displayNum;
    }

    public void setDisplayNum(Integer displayNum) {
        this.displayNum = displayNum;
    }

}
