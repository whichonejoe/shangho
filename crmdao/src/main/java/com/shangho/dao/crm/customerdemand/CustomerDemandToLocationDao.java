package com.shangho.dao.crm.customerdemand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shangho.blackcore.api.customerdemand.bean.CustomerDemandToLocationBean;
import com.shangho.blackcore.api.customerdemand.bean.LocationNameBean;
import com.shangho.dao.crm.utils.SQLFromatUtils;

public class CustomerDemandToLocationDao {
	public CustomerDemandToLocationDao() {
	}

	private static class LazyHolder {
		public static final CustomerDemandToLocationDao INSTANCE = new CustomerDemandToLocationDao();
	}

	public static CustomerDemandToLocationDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO customer_demand_to_location(customer_demand_id,location_item_id)"
			+ "VALUES(?,?);";

	public void insert(final Connection conn, final int customerDemandID, final int designatePathID)
			throws SQLException {
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT);
			int i = 0;
			psmt.setInt(++i, customerDemandID);
			psmt.setInt(++i, designatePathID);
			psmt.addBatch();
			psmt.executeBatch();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE = "DELETE FROM customer_demand_to_location WHERE customer_demand_id=?;";

	public void delete(final Connection conn, final int customerDemandID) throws SQLException {
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);
			int i = 0;
			psmt.setInt(++i, customerDemandID);
			psmt.addBatch();
			psmt.executeBatch();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT = "SELECT customer_demand_id,location_item_id "
			+ "FROM customer_demand_to_location ";

	public List<CustomerDemandToLocationBean> list(final Connection conn, final List<Integer> customerDemandID,
			final List<Integer> locationItemIDs) throws SQLException {
		PreparedStatement psmt = null;
		final List<CustomerDemandToLocationBean> list = new ArrayList<CustomerDemandToLocationBean>();
		try {
			String statement = "";
			int x = 0;
			if (!customerDemandID.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatementWithInt(customerDemandID, "customer_demand_id");

			if (!locationItemIDs.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatementWithInt(locationItemIDs, "location_item_id");

			psmt = conn.prepareStatement(SELECT + statement);
			final ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new CustomerDemandToLocationBean(rs.getInt("customer_demand_id"),
						rs.getInt("location_item_id")));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_CUSTOMER_ID = "SELECT DISTINCT customer_demand_id FROM "
			+ "customer_demand_to_location ";

	public List<Integer> listCustomerDemandID(final Connection conn, final List<Integer> locationItemIDs)
			throws SQLException {
		PreparedStatement psmt = null;
		final List<Integer> list = new ArrayList<Integer>();
		try {
			String statement = "";
			int x = 0;
			if (!locationItemIDs.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatementWithInt(locationItemIDs, "location_item_id");

			psmt = conn.prepareStatement(SELECT_CUSTOMER_ID + statement);
			final ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt("customer_demand_id"));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_ALL_BY_ID = "SELECT B.status,B.name,B.id "
			+ "FROM customer_demand_to_location A LEFT JOIN location_range_item B ON "
			+ "A.location_item_id = B.id WHERE A.customer_demand_id = ?";

	public List<LocationNameBean> list(final Connection conn, final int customerDemandID) throws SQLException {
		PreparedStatement psmt = null;
		final List<LocationNameBean> list = new ArrayList<LocationNameBean>();
		try {
			psmt = conn.prepareStatement(SELECT_ALL_BY_ID);

			int i = 0;
			psmt.setInt(++i, customerDemandID);

			final ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LocationNameBean(rs.getInt("id"), rs.getString("name"), rs.getString("status")));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

}
