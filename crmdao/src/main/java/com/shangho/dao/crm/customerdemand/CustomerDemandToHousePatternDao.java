package com.shangho.dao.crm.customerdemand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shangho.blackcore.api.customerdemand.bean.CustomerDemandToHousePatternBean;
import com.shangho.blackcore.api.customerdemand.bean.HousePatternNameBean;
import com.shangho.dao.crm.utils.SQLFromatUtils;

public class CustomerDemandToHousePatternDao {
	public CustomerDemandToHousePatternDao() {
	}

	private static class LazyHolder {
		public static final CustomerDemandToHousePatternDao INSTANCE = new CustomerDemandToHousePatternDao();
	}

	public static CustomerDemandToHousePatternDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO customer_demand_to_house_pattern(customer_demand_id,house_pattern_item_id)"
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

	private final static String DELETE = "DELETE FROM customer_demand_to_house_pattern WHERE customer_demand_id=?;";

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

	private final static String SELECT = "SELECT customer_demand_id,house_pattern_item_id "
			+ "FROM customer_demand_to_house_pattern ";

	public List<CustomerDemandToHousePatternBean> list(final Connection conn, final List<Integer> customerDemandID,
			final List<Integer> housePatternItemIDs) throws SQLException {
		PreparedStatement psmt = null;
		final List<CustomerDemandToHousePatternBean> list = new ArrayList<CustomerDemandToHousePatternBean>();
		try {
			String statement = "";
			int x = 0;
			if (!customerDemandID.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatementWithInt(customerDemandID, "customer_demand_id");

			if (!housePatternItemIDs.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatementWithInt(housePatternItemIDs, "house_pattern_item_id");

			psmt = conn.prepareStatement(SELECT + statement);
			final ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new CustomerDemandToHousePatternBean(rs.getInt("customer_demand_id"),
						rs.getInt("house_pattern_item_id")));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_CUSTOMER_ID = "SELECT DISTINCT customer_demand_id FROM "
			+ "customer_demand_to_house_pattern ";

	public List<Integer> listCustomerDemandID(final Connection conn, final List<Integer> housePatternItemIDs)
			throws SQLException {
		PreparedStatement psmt = null;
		final List<Integer> list = new ArrayList<Integer>();
		try {
			String statement = "";
			int x = 0;
			if (!housePatternItemIDs.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatementWithInt(housePatternItemIDs, "house_pattern_item_id");

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

	private final static String SELECT_ALL_BY_ID = "SELECT B.id,B.sort,B.name,B.status FROM "
			+ "customer_demand_to_house_pattern A LEFT JOIN house_pattern_item B ON "
			+ "A.house_pattern_item_id = B.id WHERE A.customer_demand_id = ?;";

	public List<HousePatternNameBean> list(final Connection conn, final int customerDemandID) throws SQLException {
		PreparedStatement psmt = null;
		final List<HousePatternNameBean> list = new ArrayList<HousePatternNameBean>();
		try {
			psmt = conn.prepareStatement(SELECT_ALL_BY_ID);

			int i = 0;
			psmt.setInt(++i, customerDemandID);

			final ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new HousePatternNameBean(rs.getInt("id"), rs.getString("name"), rs.getString("status"),
						rs.getInt("sort")));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

}
