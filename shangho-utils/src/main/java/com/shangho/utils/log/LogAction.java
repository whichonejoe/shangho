package com.shangho.utils.log;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogAction {
	private final static Logger logger = LoggerFactory.getLogger(LogAction.class);

	private ThreadLocal<LogBean> threadLocal = new ThreadLocal<LogBean>();

	private static class SingletonHolder {
		private static LogAction instance = new LogAction();
	}

	private LogAction() {
	}

	public static LogAction getInstance() {
		return SingletonHolder.instance;
	}

	public void initial(Logger logger, String action) {

		LogBean logbean = new LogBean(UUID.randomUUID().toString().replace("-", ""), logger);
		logbean.setStarttime(System.currentTimeMillis());
		logbean.setAction(action);

		threadLocal.set(logbean);

	}

	public void writeRecord(int status, String message) {

		this.threadLocal.get().setStatus(status);

		if (null != message) {
			this.threadLocal.get().setMessage(message);
		}

		this.threadLocal.get()
				.setPerformance(String.valueOf(System.currentTimeMillis() - this.threadLocal.get().getStarttime()));

		logger.info(this.threadLocal.get().toString());
	}

	public void setUserId(String remoteip) {
		this.threadLocal.get().setUserid(remoteip);
	}

	public void setRemoteIP(String remoteip) {
		this.threadLocal.get().setRemoteip(remoteip);
	}

	public boolean isSettingLog() {
		if (this.threadLocal.get() == null) {
			return false;
		}

		return true;
	}

	public void setAction(String action) {
		this.threadLocal.get().setAction(action);
	}

	public void setContent(String content) {
		this.threadLocal.get().setContent(content);
	}

	public void setUUID(String id) {
		this.threadLocal.get().setUuid(id);
	}

	public String getUUID() {
		return this.threadLocal.get().getUuid();
	}

	public void warn(String str) {
		threadLocal.get().getLogger().warn(threadLocal.get().getUuid() + "|" + str);
	}

	public void info(String str) {
		if (threadLocal.get().getLogger().isInfoEnabled()) {
			threadLocal.get().getLogger().info(threadLocal.get().getUuid() + "|" + str);
		}
	}

	public void debug(String str) {
		if (threadLocal.get().getLogger().isDebugEnabled()) {
			threadLocal.get().getLogger().debug(threadLocal.get().getUuid() + "|" + str);
		}
	}

	public void error(String str, Throwable e) {
		threadLocal.get().getLogger().error(threadLocal.get().getUuid() + "|" + str, e);
	}

	public void trace(String str) {
		if (threadLocal.get().getLogger().isTraceEnabled()) {
			threadLocal.get().getLogger().trace(threadLocal.get().getUuid() + "|" + str);
		}
	}
}
