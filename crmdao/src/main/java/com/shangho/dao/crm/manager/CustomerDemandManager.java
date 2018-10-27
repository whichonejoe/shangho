package com.shangho.dao.crm.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.shangho.blackcore.api.customerdemand.response.ListCustomerDemandResponse;
import com.shangho.dao.connection.ProxoolConnection;
import com.shangho.dao.crm.customerdemand.CustomerDemandDao;
import com.shangho.dao.crm.customerdemand.CustomerDemandToDesignatePathDao;
import com.shangho.dao.crm.customerdemand.CustomerDemandToHousePatternDao;
import com.shangho.dao.crm.customerdemand.CustomerDemandToLocationDao;
import com.shangho.dao.crm.customerdemand.CustomerDemandToSpecialDemandDao;
import com.shangho.dao.crm.designatepath.DesignatePathDao;
import com.shangho.dao.crm.housepattern.HousePatternItemDao;
import com.shangho.dao.crm.location.LocationRangeItemDao;
import com.shangho.dao.crm.specialdemand.SpecialDemandItemDao;

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
	 * @param budgetmax
	 * @param budgetminimum
	 * @param sqmax
	 * @param sqminimum
	 * @param houseagemax
	 * @param houseageminimum
	 * @param categories
	 * @param names
	 * @param housepatternitemids
	 * @param locationitemids
	 * @param specialdemanditemids
	 * @param designateids
	 * @return
	 * @throws SQLException
	 */
	public List<ListCustomerDemandResponse> list(final String status, final int budgetmax, final int budgetminimum,
			final int sqmax, final int sqminimum, final int houseagemax, final int houseageminimum,
			final List<Integer> categories, final List<String> names, final List<Integer> housepatternitemids,
			final List<Integer> locationitemids, final List<Integer> specialdemanditemids,
			final List<Integer> designateids) throws SQLException {
		Connection conn = null;
		List<ListCustomerDemandResponse> list = new ArrayList<ListCustomerDemandResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			final List<Integer> ids = handleCustomerDemandIDListFormat(conn, housepatternitemids, locationitemids,
					specialdemanditemids, designateids);

			list = CustomerDemandDao.getInstance().list(conn, status, budgetmax, budgetminimum, sqmax, sqminimum,
					houseagemax, houseageminimum, categories, names, ids);
			handleRelationshipInfo(conn, list);
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
	 * 取得關聯表的資訊
	 * 
	 * @param conn
	 * @param list
	 * @throws SQLException
	 */
	private void handleRelationshipInfo(final Connection conn, final List<ListCustomerDemandResponse> list)
			throws SQLException {
		for (final ListCustomerDemandResponse entity : list) {
			entity.setDesignates(CustomerDemandToDesignatePathDao.getInstance().list(conn, entity.getId()));
			entity.setHousepatternitems(CustomerDemandToHousePatternDao.getInstance().list(conn, entity.getId()));
			entity.setLocationitems(CustomerDemandToLocationDao.getInstance().list(conn, entity.getId()));
			entity.setSpecialdemanditems(CustomerDemandToSpecialDemandDao.getInstance().list(conn, entity.getId()));
		}
	}

	/**
	 * 查詢-把關連表的ID 整理起來
	 * 
	 * @param conn
	 * @param housepatternitemids
	 * @param locationitemids
	 * @param specialdemanditemids
	 * @param designateids
	 * @return
	 * @throws SQLException
	 */
	private List<Integer> handleCustomerDemandIDListFormat(final Connection conn,
			final List<Integer> housepatternitemids, final List<Integer> locationitemids,
			final List<Integer> specialdemanditemids, final List<Integer> designateids) throws SQLException {
		List<Integer> ids = new ArrayList<Integer>();
		if (!designateids.isEmpty())
			ids = handleDuplicateRemoved(
					CustomerDemandToDesignatePathDao.getInstance().listCustomerDemandID(conn, designateids));
		if (!locationitemids.isEmpty())
			ids = handleDuplicateRemoved(
					CustomerDemandToLocationDao.getInstance().listCustomerDemandID(conn, locationitemids));
		if (!housepatternitemids.isEmpty())
			ids = handleDuplicateRemoved(
					CustomerDemandToHousePatternDao.getInstance().listCustomerDemandID(conn, housepatternitemids));
		if (!specialdemanditemids.isEmpty())
			ids = handleDuplicateRemoved(
					CustomerDemandToSpecialDemandDao.getInstance().listCustomerDemandID(conn, specialdemanditemids));
		return ids;
	}

	/**
	 * 去除重複
	 * 
	 * @param ids
	 * @param customerDemandIDsWithSpecialDemandid
	 * @return
	 */
	private List<Integer> handleDuplicateRemoved(final List<Integer> ids) {
		final List<Integer> customerDemandIDs = new ArrayList<Integer>();
		customerDemandIDs.addAll(ids);
		final Set<Integer> set = new HashSet<Integer>();
		set.addAll(customerDemandIDs);
		customerDemandIDs.clear();
		customerDemandIDs.addAll(set);
		return customerDemandIDs;
	}

	/**
	 * 檢查 要updata的資訊
	 * 
	 * @param id
	 * @param objectCategoryID
	 * @param housepatternitemids
	 * @param locationitemids
	 * @param specialdemanditemids
	 * @param designatepathids
	 * @return
	 * @throws SQLException
	 */
	public boolean checkUpdateInfo(final int id, final int objectCategoryID, final List<Integer> housepatternitemids,
			final List<Integer> locationitemids, final List<Integer> specialdemanditemids,
			final List<Integer> designatepathids) throws SQLException {
		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			if (!CustomerDemandDao.getInstance().isExist(conn, id))
				return false;
			if (!ObjectManager.getInstance().isCategoryExisted(objectCategoryID))
				return false;
			if (HousePatternItemDao.getInstance().count(conn, housepatternitemids) != housepatternitemids.size())
				return false;
			if (LocationRangeItemDao.getInstance().count(conn, locationitemids) != locationitemids.size())
				return false;
			if (SpecialDemandItemDao.getInstance().count(conn, specialdemanditemids) != specialdemanditemids.size())
				return false;
			if (DesignatePathDao.getInstance().count(conn, designatepathids) != designatepathids.size())
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
	 * 檢查要新增的資訊
	 * 
	 * @param objectCategoryID
	 * @param housepatternitemids
	 * @param locationitemids
	 * @param specialdemanditemids
	 * @param designatepathids
	 * @return
	 * @throws SQLException
	 */
	public boolean checkInsertInfo(final int objectCategoryID, final List<Integer> housepatternitemids,
			final List<Integer> locationitemids, final List<Integer> specialdemanditemids,
			final List<Integer> designatepathids) throws SQLException {
		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectCRM();
			conn.setAutoCommit(false);
			if (HousePatternItemDao.getInstance().count(conn, housepatternitemids) != housepatternitemids.size())
				return false;
			if (LocationRangeItemDao.getInstance().count(conn, locationitemids) != locationitemids.size())
				return false;
			if (SpecialDemandItemDao.getInstance().count(conn, specialdemanditemids) != specialdemanditemids.size())
				return false;
			if (DesignatePathDao.getInstance().count(conn, designatepathids) != designatepathids.size())
				return false;
			if (!ObjectManager.getInstance().isCategoryExisted(objectCategoryID))
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

}
