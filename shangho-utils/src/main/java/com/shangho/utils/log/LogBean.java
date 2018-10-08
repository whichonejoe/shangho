package com.shangho.utils.log;

import java.io.Serializable;

import org.slf4j.Logger;

public class LogBean implements Serializable {
	private static final long serialVersionUID = 2530428599401757023L;
	private String uuid;
	private Logger logger;
	private String userid;
	private String remoteip;
	private String action;
	private Integer status;
	private String message;
	private Long starttime;
	private Object content;
	private String performance;

	public LogBean(String uuid, Logger logger) {
		super();
		this.uuid = uuid;
		this.logger = logger;
	}

	public String getRemoteip() {
		return remoteip;
	}

	public void setRemoteip(String remoteip) {
		this.remoteip = remoteip;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public Long getStarttime() {
		return starttime;
	}

	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Logger getLogger() {
		return logger;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(uuid);
		builder.append("|");
		builder.append(action);
		builder.append("|");
		builder.append(userid);
		builder.append("|");
		builder.append(content);
		builder.append("|");
		builder.append(status);
		builder.append("|");
		builder.append(message);
		builder.append("|");
		builder.append(remoteip);
		builder.append("|");
		builder.append(performance);
		return builder.toString();
	}
}
