package com.shangho.dao.crm.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shangho.blackcore.api.object.response.ListObjectCategoryResponse;
import com.shangho.dao.connection.ProxoolConnection;
import com.shangho.dao.crm.object.ObjectCategoryDao;

public class ObjectManager {
	private ObjectManager() {
	}

	private static class LazyHolder {
		public static final ObjectManager INSTANCE = new ObjectManager();
	}

	public static ObjectManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	/**
	 * 新增物件分類
	 * 
	 * @param status
	 * @param type
	 * @param sort
	 * @param name
	 * @param description
	 * @return
	 * @throws SQLException
	 */
	public int insertCategory(final String status, final String type, final int sort, final String name,
			final String description) throws SQLException {
		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			id = ObjectCategoryDao.getInstance().insert(conn, status, type, sort, name, description);
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
	 * 修改 物件 分類
	 * 
	 * @param ID
	 * @param status
	 * @param type
	 * @param sort
	 * @param name
	 * @param description
	 * @throws SQLException
	 */
	public void updateCategory(final int ID, final String status, final String type, final int sort, final String name,
			final String description) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			ObjectCategoryDao.getInstance().update(conn, ID, status, type, sort, name, description);
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
			ObjectCategoryDao.getInstance().delete(conn, ID);
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
	public boolean isCategoryExisted(final int ID) throws SQLException {
		Connection conn = null;
		boolean isExisted = false;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			isExisted = ObjectCategoryDao.getInstance().isExist(conn, ID);
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
	 * 查詢 分類
	 * 
	 * @param status
	 * @param names
	 * @return
	 * @throws SQLException
	 */
	public List<ListObjectCategoryResponse> listCategory(final String status, final String type,
			final List<String> names, final String sortOrderBy) throws SQLException {
		Connection conn = null;
		List<ListObjectCategoryResponse> list = new ArrayList<ListObjectCategoryResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			list = ObjectCategoryDao.getInstance().list(conn, status, type, names, sortOrderBy);
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
