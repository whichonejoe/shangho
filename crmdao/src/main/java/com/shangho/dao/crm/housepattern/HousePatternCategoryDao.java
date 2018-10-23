package com.shangho.dao.crm.housepattern;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.shangho.blackcore.api.housepattern.response.ListHousePatternCategoryResponse;
import com.shangho.dao.crm.utils.SQLFromatUtils;

public class HousePatternCategoryDao {
	public HousePatternCategoryDao() {
	}

	private static class LazyHolder {
		public static final HousePatternCategoryDao INSTANCE = new HousePatternCategoryDao();
	}

	public static HousePatternCategoryDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO house_pattern_category(status,sort,name,creation_time)"
			+ "VALUES(?,?,?,NOW());";

	public int insert(final Connection conn, final String status, final int sort, final String name)
			throws SQLException {
		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setString(++i, status);
			psmt.setInt(++i, sort);
			psmt.setString(++i, name);
			psmt.executeUpdate();

			final ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("house_pattern_category insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String UPDATE = "UPDATE house_pattern_category SET status=?,sort=?,name=? WHERE id=?;";

	public void update(final Connection conn, final int ID, final String status, final int sort, final String name)
			throws SQLException {
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);
			int i = 0;
			psmt.setString(++i, status);
			psmt.setInt(++i, sort);
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

	private final static String DELETE = "DELETE FROM house_pattern_category WHERE id=?;";

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

	private final static String SELECT_BY_ID = "SELECT name FROM house_pattern_category WHERE " + "id = ? ;";

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

	private final static String SELECT = "SELECT id,status,sort,name FROM house_pattern_category ";

	public List<ListHousePatternCategoryResponse> list(final Connection conn, final String status, final List<String> names,
			final String sortOrderBy) throws SQLException {
		PreparedStatement psmt = null;
		final List<ListHousePatternCategoryResponse> list = new ArrayList<ListHousePatternCategoryResponse>();
		try {
			String statement = "";
			int x = 0;
			if (!names.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatement(names, "name");
			if (!StringUtils.isBlank(status))
				statement = SQLFromatUtils.formatWhereDescription(x++, " status=? ", statement);
			if (!StringUtils.isBlank(sortOrderBy))
				statement += " ORDER BY sort " + sortOrderBy;
			psmt = conn.prepareStatement(SELECT + statement);

			int i = 0;
			if (!StringUtils.isBlank(status))
				psmt.setString(++i, status);

			final ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ListHousePatternCategoryResponse(rs.getInt("id"), rs.getString("status"), rs.getInt("sort"),
						rs.getString("name")));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

}
