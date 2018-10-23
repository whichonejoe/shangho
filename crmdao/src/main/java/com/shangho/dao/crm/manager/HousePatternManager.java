package com.shangho.dao.crm.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shangho.blackcore.api.housepattern.response.ListHousePatternCategoryResponse;
import com.shangho.blackcore.api.housepattern.response.ListHousePatternItemResponse;
import com.shangho.dao.connection.ProxoolConnection;
import com.shangho.dao.crm.housepattern.HousePatternCategoryDao;
import com.shangho.dao.crm.housepattern.HousePatternItemDao;

public class HousePatternManager {
	private HousePatternManager() {
	}

	private static class LazyHolder {
		public static final HousePatternManager INSTANCE = new HousePatternManager();
	}

	public static HousePatternManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	/**
	 * 新增格局分類
	 * 
	 * @param status
	 * @param sort
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public int insertCategory(final String status, final int sort, final String name) throws SQLException {
		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			id = HousePatternCategoryDao.getInstance().insert(conn, status, sort, name);
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
	 * 新增 項目
	 * 
	 * @param categoryID
	 * @param status
	 * @param name
	 * @param sort
	 * @return
	 * @throws SQLException
	 */
	public int insertItem(final int categoryID, final String status, final String name, final int sort)
			throws SQLException {
		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			id = HousePatternItemDao.getInstance().insert(conn, categoryID, sort, status, name);
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
	 * @param sort
	 * @param name
	 * @throws SQLException
	 */
	public void updateCategory(final int ID, final String status, final int sort, final String name)
			throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			HousePatternCategoryDao.getInstance().update(conn, ID, status, sort, name);
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
	 * @param status
	 * @param name
	 * @param sort
	 * @throws SQLException
	 */
	public void updateItem(final int ID, final int categoryID, final String status, final String name, final int sort)
			throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			HousePatternItemDao.getInstance().update(conn, ID, categoryID, sort, status, name);
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
			HousePatternItemDao.getInstance().deleteWithCategoryID(conn, ID);
			HousePatternCategoryDao.getInstance().delete(conn, ID);
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
			HousePatternItemDao.getInstance().delete(conn, ID);
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
	public boolean isCategoryExisted(final int ID) throws SQLException {
		Connection conn = null;
		boolean isExisted = false;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			isExisted = HousePatternCategoryDao.getInstance().isExist(conn, ID);
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
	 * 檢查修改項目的傳入
	 * 
	 * @param ID
	 * @return
	 * @throws SQLException
	 */
	public boolean checkItemUpdateInfo(final int categoryID, final int itemID) throws SQLException {
		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			if (!HousePatternCategoryDao.getInstance().isExist(conn, categoryID))
				return false;
			if (!HousePatternItemDao.getInstance().isExist(conn, itemID))
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

	public boolean isItemExisted(final int ID) throws SQLException {
		Connection conn = null;
		boolean isExisted = false;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			isExisted = HousePatternItemDao.getInstance().isExist(conn, ID);
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
	public List<ListHousePatternCategoryResponse> listCategory(final String status, final List<String> names,
			final String sortOrderBy) throws SQLException {
		Connection conn = null;
		List<ListHousePatternCategoryResponse> list = new ArrayList<ListHousePatternCategoryResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			list = HousePatternCategoryDao.getInstance().list(conn, status, names, sortOrderBy);
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	/**
	 * 查詢分類
	 * 
	 * @param status
	 * @param categories
	 * @param names
	 * @param sortOrderBy
	 * @return
	 * @throws SQLException
	 */
	public List<ListHousePatternItemResponse> listItem(final String status, final List<Integer> categories,
			final List<String> names, final String sortOrderBy) throws SQLException {
		Connection conn = null;
		List<ListHousePatternItemResponse> list = new ArrayList<ListHousePatternItemResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			list = HousePatternItemDao.getInstance().list(conn, categories, status, names);
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
