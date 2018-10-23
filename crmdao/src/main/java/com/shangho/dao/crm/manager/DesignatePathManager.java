package com.shangho.dao.crm.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shangho.blackcore.api.designatepath.response.ListDesignatePathResponse;
import com.shangho.dao.connection.ProxoolConnection;
import com.shangho.dao.crm.designatepath.DesignatePathDao;

public class DesignatePathManager {
	private DesignatePathManager() {
	}

	private static class LazyHolder {
		public static final DesignatePathManager INSTANCE = new DesignatePathManager();
	}

	public static DesignatePathManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	/**
	 * 新增指命地點
	 * 
	 * @param status
	 * @param country
	 * @param province
	 * @param city
	 * @param township
	 * @param village
	 * @param street
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public int insert(final String status, final String country, final String province, final String city,
			final String township, final String village, final String street, final String name) throws SQLException {
		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			id = DesignatePathDao.getInstance().insert(conn, status, country, province, city, township, village, street,
					name);
			conn.commit();
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed())
				conn.close();
		}
		return id;
	}

	/**
	 * 修改指命地點
	 * 
	 * @param ID
	 * @param status
	 * @param country
	 * @param province
	 * @param city
	 * @param township
	 * @param village
	 * @param street
	 * @param name
	 * @throws SQLException
	 */
	public void update(final int ID, final String status, final String country, final String province,
			final String city, final String township, final String village, final String street, final String name)
			throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			DesignatePathDao.getInstance().update(conn, ID, status, country, province, city, township, village, street,
					name);
			conn.commit();
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed())
				conn.close();
		}
	}

	/**
	 * 刪除
	 * 
	 * @param ID
	 * @throws SQLException
	 */
	public void delete(final int ID) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			DesignatePathDao.getInstance().delete(conn, ID);
			conn.commit();
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed())
				conn.close();
		}
	}

	/**
	 * 檢查是否存在
	 * 
	 * @param ID
	 * @param status
	 * @return
	 * @throws SQLException
	 */
	public boolean isExisted(final int ID) throws SQLException {
		Connection conn = null;
		boolean isExisted = false;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			isExisted = DesignatePathDao.getInstance().isExist(conn, ID);
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isExisted;
	}

	/**
	 * 查詢
	 * 
	 * @param status
	 * @param names
	 * @return
	 * @throws SQLException
	 */
	public List<ListDesignatePathResponse> list(final String status, final List<String> cities,
			final List<String> townships, final List<String> villages, final List<String> streets,
			final List<String> names) throws SQLException {
		Connection conn = null;
		List<ListDesignatePathResponse> list = new ArrayList<ListDesignatePathResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			list = DesignatePathDao.getInstance().list(conn, status, cities, townships, villages, streets, names);
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

}
