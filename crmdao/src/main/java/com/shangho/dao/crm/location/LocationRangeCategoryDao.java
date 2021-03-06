package com.shangho.dao.crm.location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.shangho.blackcore.api.location.response.ListLocationCategoryResponse;
import com.shangho.dao.crm.utils.SQLFromatUtils;

public class LocationRangeCategoryDao {
	public LocationRangeCategoryDao() {
	}

	private static class LazyHolder {
		public static final LocationRangeCategoryDao INSTANCE = new LocationRangeCategoryDao();
	}

	public static LocationRangeCategoryDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO location_range_category(status,name,description,creation_time)VALUES"
			+ "(?,?,?,NOW());";

	public int insert(final Connection conn, final String status, final String name, final String description)
			throws SQLException {
		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setString(++i, status);
			psmt.setString(++i, name);
			psmt.setString(++i, description);
			psmt.executeUpdate();

			final ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("location_range_category insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String UPDATE = "UPDATE location_range_category SET status=?,name=?,description=? WHERE id=?;";

	public void update(final Connection conn, final int ID, final String status, final String name,
			final String description) throws SQLException {
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);
			int i = 0;
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

	private final static String DELETE = "DELETE FROM location_range_category WHERE id=?;";

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

	private final static String SELECT_NAME = "SELECT name FROM location_range_category WHERE "
			+ "id = ? AND status = ?;";

	public boolean isExist(final Connection conn, final int ID, final String status) throws SQLException {
		PreparedStatement psmt = null;
		boolean isExist = false;
		try {
			psmt = conn.prepareStatement(SELECT_NAME);

			int i = 0;
			psmt.setInt(++i, ID);
			psmt.setString(++i, status);

			ResultSet rs = psmt.executeQuery();

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

	private final static String SELECT_BY_ID = "SELECT name FROM location_range_category WHERE " + "id = ? ;";

	public boolean isExist(final Connection conn, final int ID) throws SQLException {
		PreparedStatement psmt = null;
		boolean isExist = false;
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID);

			int i = 0;
			psmt.setInt(++i, ID);

			ResultSet rs = psmt.executeQuery();

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

	private final static String SELECT = "SELECT id,status,name,description FROM location_range_category ";

	public List<ListLocationCategoryResponse> list(final Connection conn, final String status, final List<String> names)
			throws SQLException {
		PreparedStatement psmt = null;
		final List<ListLocationCategoryResponse> list = new ArrayList<ListLocationCategoryResponse>();
		try {
			String statement = "";
			int x = 0;
			if (!names.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatement(names, "name");
			if (!StringUtils.isBlank(status))
				statement = SQLFromatUtils.formatWhereDescription(x++, " status=? ", statement);

			psmt = conn.prepareStatement(SELECT + statement);

			int i = 0;
			if (!StringUtils.isBlank(status))
				psmt.setString(++i, status);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ListLocationCategoryResponse(rs.getInt("id"), rs.getString("status"), rs.getString("name"),
						rs.getString("description")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

}
