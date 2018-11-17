package com.shangho.dao.crm.specialdemand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.shangho.blackcore.api.specialdemand.response.ListSpecialDemandItemResponse;
import com.shangho.dao.crm.utils.SQLFromatUtils;

public class SpecialDemandItemDao {
	public SpecialDemandItemDao() {
	}

	private static class LazyHolder {
		public static final SpecialDemandItemDao INSTANCE = new SpecialDemandItemDao();
	}

	public static SpecialDemandItemDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO special_demand_item(special_demand_category_id,refer_id,"
			+ "status,name,description,creation_time)VALUES(?,?,?,?,?,NOW());";

	public int insert(final Connection conn, final int categoryID, final int referID, final String status,
			final String name, final String description) throws SQLException {
		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, categoryID);
			if (referID > 0)// 外鍵不能為0
				psmt.setInt(++i, referID);
			else
				psmt.setNull(++i, java.sql.Types.INTEGER);
			psmt.setString(++i, status);
			psmt.setString(++i, name);
			psmt.setString(++i, description);
			psmt.executeUpdate();

			final ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("special_demand_item insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String UPDATE = "UPDATE special_demand_item SET special_demand_category_id=?,refer_id=?,"
			+ "status=?,name=?,description=? WHERE id=?;";

	public void update(final Connection conn, final int ID, final int categoryID, final int referID,
			final String status, final String name, final String description) throws SQLException {
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);
			int i = 0;
			psmt.setInt(++i, categoryID);
			if (referID > 0)// 外鍵不能為0
				psmt.setInt(++i, referID);
			else
				psmt.setNull(++i, java.sql.Types.INTEGER);
			psmt.setString(++i, status);
			psmt.setString(++i, name);
			psmt.setString(++i, description);
			psmt.setInt(++i, ID);
			psmt.addBatch();
			psmt.executeBatch();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE = "DELETE FROM special_demand_item WHERE id=?;";

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

	private final static String DELETE_WITH_REFERID = "DELETE FROM special_demand_item WHERE " + "refer_id=?;";

	public void deleteWithReferID(final Connection conn, final int referID) throws SQLException {
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_WITH_REFERID);
			int i = 0;
			psmt.setInt(++i, referID);
			psmt.addBatch();
			psmt.executeBatch();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE_WITH_CATEGORY = "DELETE FROM special_demand_item WHERE "
			+ "special_demand_category_id=?;";

	public void deleteWithCategoryID(final Connection conn, final int categoryID) throws SQLException {
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_WITH_CATEGORY);
			int i = 0;
			psmt.setInt(++i, categoryID);
			psmt.addBatch();
			psmt.executeBatch();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_NAME = "SELECT name FROM special_demand_item WHERE " + "id = ? AND status = ?;";

	public boolean isExist(final Connection conn, final int ID, final String status) throws SQLException {
		PreparedStatement psmt = null;
		boolean isExist = false;
		try {
			psmt = conn.prepareStatement(SELECT_NAME);

			int i = 0;
			psmt.setInt(++i, ID);
			psmt.setString(++i, status);

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

	private final static String SELECT_BY_ID = "SELECT name FROM special_demand_item WHERE " + "id = ? ;";

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

	private final static String SELECT = "SELECT B.name AS category_name,A.status,A.name,A.description,A.id,"
			+ "A.special_demand_category_id,A.refer_id,CASE WHEN A.refer_id>0 THEN (SELECT C.name FROM special_demand_item C "
			+ "WHERE A.refer_id = C.id LIMIT 1)ELSE NULL END AS refer_name FROM special_demand_item A "
			+ "LEFT JOIN special_demand_category B ON A.special_demand_category_id = B.id ";

	public List<ListSpecialDemandItemResponse> list(final Connection conn, final List<Integer> categories,
			final List<Integer> refers, final String status, final List<String> names) throws SQLException {
		PreparedStatement psmt = null;
		final List<ListSpecialDemandItemResponse> list = new ArrayList<ListSpecialDemandItemResponse>();
		try {
			String statement = "";
			int x = 0;
			if (!categories.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatementWithInt(categories, "A.special_demand_category_id");
			if (!refers.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatementWithInt(refers, "A.refer_id");
			if (!names.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatement(names, "A.name");
			if (!StringUtils.isBlank(status))
				statement = SQLFromatUtils.formatWhereDescription(x++, " A.status=? ", statement);

			psmt = conn.prepareStatement(SELECT + statement);

			int i = 0;
			if (!StringUtils.isBlank(status))
				psmt.setString(++i, status);

			final ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ListSpecialDemandItemResponse(rs.getInt("id"), rs.getInt("special_demand_category_id"),
						rs.getString("category_name"), rs.getInt("refer_id"), rs.getString("refer_name"),
						rs.getString("status"), rs.getString("name"), rs.getString("description")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_ITEM = "SELECT COUNT(*) AS c FROM special_demand_item ";

	public int count(final Connection conn, final List<Integer> ids) throws SQLException {
		PreparedStatement psmt = null;
		int count = 0;
		try {
			String statement = "";
			int x = 0;
			if (!ids.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatementWithInt(ids, "id");

			psmt = conn.prepareStatement(SELECT_ITEM + statement);

			final ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("c");
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return count;
	}
}
