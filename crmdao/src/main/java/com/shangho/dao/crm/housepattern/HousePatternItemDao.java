package com.shangho.dao.crm.housepattern;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.shangho.blackcore.api.housepattern.response.ListHousePatternItemResponse;
import com.shangho.dao.crm.utils.SQLFromatUtils;

public class HousePatternItemDao {
	public HousePatternItemDao() {
	}

	private static class LazyHolder {
		public static final HousePatternItemDao INSTANCE = new HousePatternItemDao();
	}

	public static HousePatternItemDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO house_pattern_item(house_pattern_id,sort,"
			+ "status,name,creation_time)VALUES(?,?,?,?,NOW());";

	public int insert(final Connection conn, final int categoryID, final int sort, final String status,
			final String name) throws SQLException {
		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, categoryID);
			psmt.setInt(++i, sort);
			psmt.setString(++i, status);
			psmt.setString(++i, name);
			psmt.executeUpdate();

			final ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("house_pattern_item insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String UPDATE = "UPDATE house_pattern_item SET house_pattern_id=?,sort=?,"
			+ "status=?,name=? WHERE id=?;";

	public void update(final Connection conn, final int ID, final int categoryID, final int sort, final String status,
			final String name) throws SQLException {
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);
			int i = 0;
			psmt.setInt(++i, categoryID);
			psmt.setInt(++i, sort);
			psmt.setString(++i, status);
			psmt.setString(++i, name);
			psmt.setInt(++i, ID);
			psmt.addBatch();
			psmt.executeBatch();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE = "DELETE FROM house_pattern_item WHERE id=?;";

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

	private final static String DELETE_WITH_CATEGORY = "DELETE FROM house_pattern_item WHERE " + "house_pattern_id=?;";

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

	private final static String SELECT_BY_ID = "SELECT name FROM house_pattern_item WHERE " + "id = ? ;";

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

	private final static String SELECT = "SELECT B.name AS category_name,A.status,A.name,A.sort,A.id "
			+ ",A.house_pattern_id FROM house_pattern_item A LEFT JOIN house_pattern_category B ON A.house_pattern_id = B.id ";

	public List<ListHousePatternItemResponse> list(final Connection conn, final List<Integer> categories,
			final String status, final List<String> names) throws SQLException {
		PreparedStatement psmt = null;
		final List<ListHousePatternItemResponse> list = new ArrayList<ListHousePatternItemResponse>();
		try {
			String statement = "";
			int x = 0;
			if (!categories.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatementWithInt(categories, "A.house_pattern_id");
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
				list.add(new ListHousePatternItemResponse(rs.getInt("id"), rs.getInt("house_pattern_id"),
						rs.getString("category_name"), rs.getString("status"), rs.getString("name"),
						rs.getInt("sort")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_ITEM = "SELECT COUNT(*) AS c FROM house_pattern_item ";

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
