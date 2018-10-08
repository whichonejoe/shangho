package com.shangho.dao.crm.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.shangho.dao.connection.ProxoolConnection;
import com.shangho.dao.crm.location.LocationRangeCategoryDao;
import com.shangho.dao.crm.location.LocationRangeItemDao;

public class LocationManager {
	private LocationManager() {
	}

	private static class LazyHolder {
		public static final LocationManager INSTANCE = new LocationManager();
	}

	public static LocationManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	/**
	 * 新增分類
	 * 
	 * @param status
	 * @param name
	 * @param description
	 * @return
	 * @throws SQLException
	 */
	public int insertCategory(final String status, final String name, final String description) throws SQLException {
		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			id = LocationRangeCategoryDao.getInstance().insert(conn, status, name, description);
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
	 * 新增項目
	 * 
	 * @param categoryID
	 * @param referID
	 * @param status
	 * @param name
	 * @param description
	 * @return
	 * @throws SQLException
	 */
	public int insertItem(final int categoryID, final int referID, final String status, final String name,
			final String description) throws SQLException {
		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			id = LocationRangeItemDao.getInstance().insert(conn, categoryID, referID, status, name, description);
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
	 * 修改分類
	 * 
	 * @param ID
	 * @param status
	 * @param name
	 * @param description
	 * @throws SQLException
	 */
	public void updateCategory(final int ID, final String status, final String name, final String description)
			throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			LocationRangeCategoryDao.getInstance().update(conn, ID, status, name, description);
			conn.commit();
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed())
				conn.close();
		}
	}

	/**
	 * 修改項目
	 * 
	 * @param ID
	 * @param categoryID
	 * @param referID
	 * @param status
	 * @param name
	 * @param description
	 * @throws SQLException
	 */
	public void updateItem(final int ID, final int categoryID, final int referID, final String status,
			final String name, final String description) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			LocationRangeItemDao.getInstance().update(conn, ID, categoryID, referID, status, name, description);
			conn.commit();
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed())
				conn.close();
		}
	}

	/**
	 * 刪除分類
	 * 
	 * @param ID
	 * @throws SQLException
	 */
	public void deleteCategory(final int ID) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			LocationRangeItemDao.getInstance().deleteWithCategoryID(conn, ID);
			LocationRangeCategoryDao.getInstance().delete(conn, ID);
			conn.commit();
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed())
				conn.close();
		}
	}

	/**
	 * 刪除項目
	 * 
	 * @param ID
	 * @throws SQLException
	 */
	public void deleteItem(final int ID) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			LocationRangeCategoryDao.getInstance().delete(conn, ID);
			conn.commit();
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed())
				conn.close();
		}
	}

	/**
	 * 檢查分類是否存在
	 * 
	 * @param ID
	 * @param status
	 * @return
	 * @throws SQLException
	 */
	public boolean isCategoryExisted(final int ID, final String status) throws SQLException {
		Connection conn = null;
		boolean isExisted = false;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			isExisted = LocationRangeCategoryDao.getInstance().isExist(conn, ID, status);
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
	 * 檢查項目是否存在
	 * 
	 * @param ID
	 * @param status
	 * @return
	 * @throws SQLException
	 */
	public boolean isItemExisted(final int ID, final String status) throws SQLException {
		Connection conn = null;
		boolean isExisted = false;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			isExisted = LocationRangeCategoryDao.getInstance().isExist(conn, ID, status);
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
	 * 確認要新增的資料是否都存在!!
	 * 
	 * @param categoryID
	 * @param referID
	 * @return
	 * @throws SQLException
	 */
	public boolean checkItemInsertInfo(final int categoryID, final int referID) throws SQLException {
		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			if (!LocationRangeCategoryDao.getInstance().isExist(conn, categoryID))
				return false;
			if (referID > 0 && !LocationRangeItemDao.getInstance().isExist(conn, referID))
				return false;
			isPass = true;
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	/**
	 * 確認 要修改的資料是不是都存在
	 * 
	 * @param ID
	 * @param categoryID
	 * @param referID
	 * @return
	 * @throws SQLException
	 */
	public boolean checkItemUpdateInfo(final int ID, final int categoryID, final int referID) throws SQLException {
		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			if (!LocationRangeItemDao.getInstance().isExist(conn, ID))
				return false;
			if (!LocationRangeCategoryDao.getInstance().isExist(conn, categoryID))
				return false;
			if (referID > 0 && !LocationRangeItemDao.getInstance().isExist(conn, referID))
				return false;
			isPass = true;
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	public boolean list(final String status, final List<String> names) throws SQLException {
		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			LocationRangeCategoryDao.getInstance().list(conn, status, names);
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

}
