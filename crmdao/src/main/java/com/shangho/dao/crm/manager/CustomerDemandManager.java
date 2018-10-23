package com.shangho.dao.crm.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shangho.blackcore.api.designatepath.response.ListDesignatePathResponse;
import com.shangho.dao.connection.ProxoolConnection;
import com.shangho.dao.crm.customerdemand.CustomerDemandDao;
import com.shangho.dao.crm.customerdemand.CustomerDemandToDesignatePathDao;
import com.shangho.dao.crm.customerdemand.CustomerDemandToHousePatternDao;
import com.shangho.dao.crm.customerdemand.CustomerDemandToLocationDao;
import com.shangho.dao.crm.customerdemand.CustomerDemandToSpecialDemandDao;
import com.shangho.dao.crm.designatepath.DesignatePathDao;

public class CustomerDemandManager {
	private CustomerDemandManager() {
	}

	private static class LazyHolder {
		public static final CustomerDemandManager INSTANCE = new CustomerDemandManager();
	}

	public static CustomerDemandManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	/**
	 * 新增客戶需求
	 * 
	 * @param objectCategoryID
	 * @param status
	 * @param name
	 * @param budgetMax
	 * @param budgetMinimum
	 * @param sqMax
	 * @param sqMinimum
	 * @param houseAgeMax
	 * @param houseAgeMinimum
	 * @param housePatternItems
	 * @param locationRangeItems
	 * @param specialDemandItems
	 * @param designatePathIDs
	 * @return
	 * @throws SQLException
	 */
	public int insert(final int objectCategoryID, final String status, final String name, final int budgetMax,
			final int budgetMinimum, final int sqMax, final int sqMinimum, final int houseAgeMax,
			final int houseAgeMinimum, final List<Integer> housePatternItems, final List<Integer> locationRangeItems,
			final List<Integer> specialDemandItems, final List<Integer> designatePathIDs) throws SQLException {
		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			id = CustomerDemandDao.getInstance().insert(conn, objectCategoryID, status, name, budgetMax, budgetMinimum,
					sqMax, sqMinimum, houseAgeMax, houseAgeMinimum);
			for (final Integer itemID : designatePathIDs) {
				CustomerDemandToDesignatePathDao.getInstance().insert(conn, id, itemID);
			}
			for (final Integer itemID : locationRangeItems) {
				CustomerDemandToLocationDao.getInstance().insert(conn, id, itemID);
			}
			for (final Integer itemID : specialDemandItems) {
				CustomerDemandToSpecialDemandDao.getInstance().insert(conn, id, itemID);
			}
			for (final Integer itemID : housePatternItems) {
				CustomerDemandToHousePatternDao.getInstance().insert(conn, id, itemID);
			}
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
	 * 修改客戶需求
	 * 
	 * @param id
	 * @param objectCategoryID
	 * @param status
	 * @param name
	 * @param budgetMax
	 * @param budgetMinimum
	 * @param sqMax
	 * @param sqMinimum
	 * @param houseAgeMax
	 * @param houseAgeMinimum
	 * @param housePatternItems
	 * @param locationRangeItems
	 * @param specialDemandItems
	 * @param designatePathIDs
	 * @throws SQLException
	 */
	public void update(final int id, final int objectCategoryID, final String status, final String name,
			final int budgetMax, final int budgetMinimum, final int sqMax, final int sqMinimum, final int houseAgeMax,
			final int houseAgeMinimum, final List<Integer> housePatternItems, final List<Integer> locationRangeItems,
			final List<Integer> specialDemandItems, final List<Integer> designatePathIDs) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			CustomerDemandDao.getInstance().update(conn, id, objectCategoryID, status, name, budgetMax, budgetMinimum,
					sqMax, sqMinimum, houseAgeMax, houseAgeMinimum);

			CustomerDemandToDesignatePathDao.getInstance().delete(conn, id);
			CustomerDemandToLocationDao.getInstance().delete(conn, id);
			CustomerDemandToSpecialDemandDao.getInstance().delete(conn, id);
			CustomerDemandToHousePatternDao.getInstance().delete(conn, id);

			for (final Integer itemID : designatePathIDs) {
				CustomerDemandToDesignatePathDao.getInstance().insert(conn, id, itemID);
			}
			for (final Integer itemID : locationRangeItems) {
				CustomerDemandToLocationDao.getInstance().insert(conn, id, itemID);
			}
			for (final Integer itemID : specialDemandItems) {
				CustomerDemandToSpecialDemandDao.getInstance().insert(conn, id, itemID);
			}
			for (final Integer itemID : housePatternItems) {
				CustomerDemandToHousePatternDao.getInstance().insert(conn, id, itemID);
			}
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
	public void delete(final int id) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			CustomerDemandToDesignatePathDao.getInstance().delete(conn, id);
			CustomerDemandToLocationDao.getInstance().delete(conn, id);
			CustomerDemandToSpecialDemandDao.getInstance().delete(conn, id);
			CustomerDemandToHousePatternDao.getInstance().delete(conn, id);
			CustomerDemandDao.getInstance().delete(conn, id);
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
	public boolean isExisted(final int id) throws SQLException {
		Connection conn = null;
		boolean isExisted = false;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			isExisted = CustomerDemandDao.getInstance().isExist(conn, id);
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
