package com.shangho.dao.crm.customerdemand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shangho.blackcore.api.customerdemand.bean.CustomerDemandToSpecialDemandBean;
import com.shangho.dao.crm.utils.SQLFromatUtils;

public class CustomerDemandToSpecialDemandDao {
	public CustomerDemandToSpecialDemandDao() {
	}

	private static class LazyHolder {
		public static final CustomerDemandToSpecialDemandDao INSTANCE = new CustomerDemandToSpecialDemandDao();
	}

	public static CustomerDemandToSpecialDemandDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO customer_demand_to_special_demand(customer_demand_id,special_demand_item_id)"
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

	private final static String DELETE = "DELETE FROM customer_demand_to_special_demand WHERE customer_demand_id=?;";

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

	private final static String SELECT = "SELECT customer_demand_id,special_demand_item_id "
			+ "FROM customer_demand_to_special_demand ";

	public List<CustomerDemandToSpecialDemandBean> list(final Connection conn, final List<Integer> customerDemandID,
			final List<Integer> specialDemandItemIDs) throws SQLException {
		PreparedStatement psmt = null;
		final List<CustomerDemandToSpecialDemandBean> list = new ArrayList<CustomerDemandToSpecialDemandBean>();
		try {
			String statement = "";
			int x = 0;
			if (!customerDemandID.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatementWithInt(customerDemandID, "customer_demand_id");

			if (!specialDemandItemIDs.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatementWithInt(specialDemandItemIDs, "special_demand_item_id");

			psmt = conn.prepareStatement(SELECT + statement);
			final ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new CustomerDemandToSpecialDemandBean(rs.getInt("customer_demand_id"),
						rs.getInt("special_demand_item_id")));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

}
