package com.stone.shop.domain.manage.log;

import java.io.Serializable;
import java.util.Date;

public class LogEntity implements Serializable {

	/**
	 * 系统日志
	 */
	private static final long serialVersionUID = 1363308656639936432L;

	private String id;
	
	private String operatorId;
	
	private String operatorName;
	
	private Date time;
	
	private String ip;
	
	private String content;

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

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
