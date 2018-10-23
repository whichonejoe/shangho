package com.shangho.dao.crm.customerdemand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shangho.blackcore.api.customerdemand.bean.CustomerDemandToDesignatePathBean;
import com.shangho.dao.crm.utils.SQLFromatUtils;

public class CustomerDemandToDesignatePathDao {
	public CustomerDemandToDesignatePathDao() {
	}

	private static class LazyHolder {
		public static final CustomerDemandToDesignatePathDao INSTANCE = new CustomerDemandToDesignatePathDao();
	}

	public static CustomerDemandToDesignatePathDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO customer_demand_to_designate_path(customer_demand_id,designate_path_id)"
			+ "VALUES(?,?);";

	public int insert(final Connection conn, final int customerDemandID, final int designatePathID)
			throws SQLException {
		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, customerDemandID);
			psmt.setInt(++i, designatePathID);
			psmt.executeUpdate();

			final ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("customer_demand_to_designate_path insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String DELETE = "DELETE FROM customer_demand_to_designate_path WHERE customer_demand_id=?;";

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

	private final static String SELECT = "SELECT customer_demand_id,designate_path_id "
			+ "FROM customer_demand_to_designate_path ";

	public List<CustomerDemandToDesignatePathBean> list(final Connection conn, final List<Integer> customerDemandID,
			final List<Integer> designatePathIDs) throws SQLException {
		PreparedStatement psmt = null;
		final List<CustomerDemandToDesignatePathBean> list = new ArrayList<CustomerDemandToDesignatePathBean>();
		try {
			String statement = "";
			int x = 0;
			if (!customerDemandID.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatementWithInt(customerDemandID, "customer_demand_id");

			if (!designatePathIDs.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatementWithInt(designatePathIDs, "designate_path_id");

			psmt = conn.prepareStatement(SELECT + statement);
			final ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new CustomerDemandToDesignatePathBean(rs.getInt("customer_demand_id"),
						rs.getInt("designate_path_id")));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

}
