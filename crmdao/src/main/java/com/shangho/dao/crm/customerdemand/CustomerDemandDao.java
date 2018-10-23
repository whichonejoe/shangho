package com.shangho.dao.crm.customerdemand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.shangho.blackcore.api.object.response.ListObjectCategoryResponse;
import com.shangho.dao.crm.utils.SQLFromatUtils;

public class CustomerDemandDao {
	public CustomerDemandDao() {
	}

	private static class LazyHolder {
		public static final CustomerDemandDao INSTANCE = new CustomerDemandDao();
	}

	public static CustomerDemandDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO customer_demand(object_category_id,status,name,"
			+ "budget_max,budget_minimum,sq_max,sq_minimum,house_age_max,house_age_minimum,creation_time)"
			+ "VALUES(?,?,?,?,?,?,?,?,?,NOW());";

	public int insert(final Connection conn, final int objectCategoryID, final String status, final String name,
			final int budgetMax, final int budgetMinimum, final int sqMax, final int sqMinimum, final int houseAgeMax,
			final int houseAgeMinimum) throws SQLException {
		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, objectCategoryID);
			psmt.setString(++i, status);
			psmt.setString(++i, name);
			psmt.setInt(++i, budgetMax);
			psmt.setInt(++i, budgetMinimum);
			psmt.setInt(++i, sqMax);
			psmt.setInt(++i, sqMinimum);
			psmt.setInt(++i, houseAgeMax);
			psmt.setInt(++i, houseAgeMinimum);
			psmt.executeUpdate();

			final ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("customer_demand insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String UPDATE = "UPDATE customer_demand SET object_category_id=?,status=?,"
			+ "name=?,budget_max=?,budget_minimum=?,sq_max=?,sq_minimum=?,house_age_max=?,house_age_minimum=? WHERE id=?;";

	public void update(final Connection conn, final int ID, final int objectCategoryID, final String status,
			final String name, final int budgetMax, final int budgetMinimum, final int sqMax, final int sqMinimum,
			final int houseAgeMax, final int houseAgeMinimum) throws SQLException {
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);
			int i = 0;
			psmt.setInt(++i, objectCategoryID);
			psmt.setString(++i, status);
			psmt.setString(++i, name);
			psmt.setInt(++i, budgetMax);
			psmt.setInt(++i, budgetMinimum);
			psmt.setInt(++i, sqMax);
			psmt.setInt(++i, sqMinimum);
			psmt.setInt(++i, houseAgeMax);
			psmt.setInt(++i, houseAgeMinimum);
			psmt.setInt(++i, ID);
			psmt.addBatch();
			psmt.executeBatch();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE = "DELETE FROM customer_demand WHERE id=?;";

	public void delete(final Connection conn, final int ID) throws SQLException {
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);
			int i = 0;
			psmt.setInt(++i, ID);
			psmt.addBatch();
			psmt.executeBatch();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_BY_ID = "SELECT name FROM customer_demand WHERE " + "id = ? ;";

	public boolean isExist(final Connection conn, final int ID) throws SQLException {
		PreparedStatement psmt = null;
		boolean isExist = false;
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID);

			int i = 0;
			psmt.setInt(++i, ID);

			final ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				isExist = true;
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isExist;
	}

	private final static String SELECT = "SELECT id,status,type,sort,name,description FROM customer_demand ";

	public List<ListObjectCategoryResponse> list(final Connection conn, final String status, final String type,
			final List<String> names, final String sortOrderBy) throws SQLException {
		PreparedStatement psmt = null;
		final List<ListObjectCategoryResponse> list = new ArrayList<ListObjectCategoryResponse>();
		try {
			String statement = "";
			int x = 0;
			if (!names.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatement(names, "name");
			if (!StringUtils.isBlank(status))
				statement = SQLFromatUtils.formatWhereDescription(x++, " status=? ", statement);
			if (!StringUtils.isBlank(type))
				statement = SQLFromatUtils.formatWhereDescription(x++, " type=?", statement);
			if (!StringUtils.isBlank(sortOrderBy))
				statement += " ORDER BY sort " + sortOrderBy;
			psmt = conn.prepareStatement(SELECT + statement);

			int i = 0;
			if (!StringUtils.isBlank(status))
				psmt.setString(++i, status);
			if (!StringUtils.isBlank(type))
				psmt.setString(++i, type);

			final ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ListObjectCategoryResponse(rs.getInt("id"), rs.getString("status"), rs.getString("type"),
						rs.getInt("sort"), rs.getString("name"), rs.getString("description")));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

}
