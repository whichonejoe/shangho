package com.shangho.dao.crm.object;

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

public class ObjectCategoryDao {
	public ObjectCategoryDao() {
	}

	private static class LazyHolder {
		public static final ObjectCategoryDao INSTANCE = new ObjectCategoryDao();
	}

	public static ObjectCategoryDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO object_category(status,type,sort,name,description,creation_time)"
			+ "VALUES(?,?,?,?,?,NOW());";

	public int insert(final Connection conn, final String status, final String type, final int sort, final String name,
			final String description) throws SQLException {
		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setString(++i, status);
			psmt.setString(++i, type);
			psmt.setInt(++i, sort);
			psmt.setString(++i, name);
			psmt.setString(++i, description);
			psmt.executeUpdate();

			final ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("object_category insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String UPDATE = "UPDATE object_category SET status=?,type=?,sort=?,name=?,description=? WHERE id=?;";

	public void update(final Connection conn, final int ID, final String status, final String type, final int sort,
			final String name, final String description) throws SQLException {
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);
			int i = 0;
			psmt.setString(++i, status);
			psmt.setString(++i, type);
			psmt.setInt(++i, sort);
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

	private final static String DELETE = "DELETE FROM object_category WHERE id=?;";

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

	private final static String SELECT_BY_ID = "SELECT name FROM object_category WHERE " + "id = ? ;";

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

	private final static String SELECT = "SELECT status,type,sort,name,description FROM object_category ";

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
				list.add(new ListObjectCategoryResponse(rs.getString("status"), rs.getString("type"), rs.getInt("sort"),
						rs.getString("name"), rs.getString("description")));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

}
