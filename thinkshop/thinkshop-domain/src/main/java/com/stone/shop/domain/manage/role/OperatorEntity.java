package com.stone.shop.domain.manage.role;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 操作员实体
 */
public class OperatorEntity implements Serializable {


    private static final long serialVersionUID = 3741984497628801569L;

    @NotEmpty(message = "操作员账号不能为空")
    @Length(max = 20, min = 5, message = "操作员账号长度是5到20位")
    private String operatorId;

    @NotEmpty(message = "密码不能为空")
    @Length(max = 50, min = 5, message = "密码为5到50位")
    private String password;

    @NotEmpty(message = "姓名不能为空")
    @Length(max = 20, message = "姓名长度不能超过50位")
    private String name;

    @Length(max = 15, message = "手机号码长度不超过15位")
    private String mobileNum;

    @Length(max = 15, message = "电话号码长度不能超过15位")
    private String teleNum;

    @Length(max = 30, message = "电子邮件不能超过30位")
    private String email;

    private String status;

    @Length(max = 40, message = "职称长度不能超过40位")
    private String jobName;

    @Length(max = 100, message = "备注不能超过100位")
    private String remark;

    private String lastTime;

    private String lastIp;

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getTeleNum() {
        return teleNum;
    }

    public void setTeleNum(String teleNum) {
        this.teleNum = teleNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

}
