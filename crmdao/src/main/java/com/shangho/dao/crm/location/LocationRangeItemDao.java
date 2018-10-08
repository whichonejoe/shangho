package com.shangho.dao.crm.location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocationRangeItemDao {
	public LocationRangeItemDao() {
	}

	private static class LazyHolder {
		public static final LocationRangeItemDao INSTANCE = new LocationRangeItemDao();
	}

	public static LocationRangeItemDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO location_range_item(location_range_category_id,refer_id,"
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
				throw new SQLException("location_range_item insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String UPDATE = "UPDATE location_range_item SET location_range_category_id=?,refer_id=?,"
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

	private final static String DELETE = "DELETE FROM location_range_item WHERE id=?;";

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

	private final static String DELETE_WITH_CATEGORY = "DELETE FROM location_range_item WHERE "
			+ "location_range_category_id=?;";

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

	private final static String SELECT_NAME = "SELECT name FROM location_range_item WHERE " + "id = ? AND status = ?;";

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

	private final static String SELECT_BY_ID = "SELECT name FROM location_range_item WHERE " + "id = ? ;";

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
}
