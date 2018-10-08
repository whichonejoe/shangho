package com.shangho.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProxoolConnection {
	private final static String PROXOOL = "proxool.";
	private final static String CRM = "crm";

	private ProxoolConnection() {
	}

	private static class LazyHolder {
		public static final ProxoolConnection INSTANCE = new ProxoolConnection();
	}

	public static ProxoolConnection getInstance() {
		return LazyHolder.INSTANCE;
	}

	public Connection connectCRM() throws SQLException {
		return DriverManager.getConnection(PROXOOL + CRM);
	}
}
