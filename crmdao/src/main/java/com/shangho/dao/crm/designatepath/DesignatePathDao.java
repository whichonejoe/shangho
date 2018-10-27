package com.shangho.dao.crm.designatepath;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.shangho.blackcore.api.designatepath.response.ListDesignatePathResponse;
import com.shangho.dao.crm.utils.SQLFromatUtils;

public class DesignatePathDao {
	public DesignatePathDao() {
	}

	private static class LazyHolder {
		public static final DesignatePathDao INSTANCE = new DesignatePathDao();
	}

	public static DesignatePathDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO designate_path(status,country,province,city,"
			+ "township,village,street,name,creation_time)VALUES(?,?,?,?,?,?,?,?,NOW());";

	public int insert(final Connection conn, final String status, final String country, final String province,
			final String city, final String township, final String village, final String street, final String name)
			throws SQLException {
		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setString(++i, status);
			psmt.setString(++i, country);
			psmt.setString(++i, province);
			psmt.setString(++i, city);
			psmt.setString(++i, township);
			psmt.setString(++i, village);
			psmt.setString(++i, street);
			psmt.setString(++i, name);

			psmt.executeUpdate();

			final ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("designate_path insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String UPDATE = "UPDATE designate_path SET status=?,country=?,province=?,city=?,"
			+ "township=?,village=?,street=?,name=? WHERE id=?;";

	public void update(final Connection conn, final int ID, final String status, final String country,
			final String province, final String city, final String township, final String village, final String street,
			final String name) throws SQLException {
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);
			int i = 0;
			psmt.setString(++i, status);
			psmt.setString(++i, country);
			psmt.setString(++i, province);
			psmt.setString(++i, city);
			psmt.setString(++i, township);
			psmt.setString(++i, village);
			psmt.setString(++i, street);
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

	private final static String DELETE = "DELETE FROM designate_path WHERE id=?;";

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

	private final static String SELECT_BY_ID = "SELECT name FROM designate_path WHERE " + "id = ? ;";

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

	private final static String SELECT = "SELECT id,status,country,province,city,township,village,street,name "
			+ "FROM designate_path ";

	public List<ListDesignatePathResponse> list(final Connection conn, final String status, final List<String> cities,
			final List<String> townships, final List<String> villages, final List<String> streets,
			final List<String> names) throws SQLException {
		PreparedStatement psmt = null;
		final List<ListDesignatePathResponse> list = new ArrayList<ListDesignatePathResponse>();
		try {
			String statement = "";
			int x = 0;
			if (!names.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatement(names, "name");
			if (!cities.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatement(cities, "city");
			if (!townships.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatement(townships, "township");
			if (!villages.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatement(villages, "village");
			if (!streets.isEmpty())
				statement = SQLFromatUtils.formatWhereDescription(x++, "", statement)
						+ SQLFromatUtils.handleSQLLikeStatement(streets, "street");

			if (!StringUtils.isBlank(status))
				statement = SQLFromatUtils.formatWhereDescription(x++, " status=? ", statement);
			psmt = conn.prepareStatement(SELECT + statement);

			int i = 0;
			if (!StringUtils.isBlank(status))
				psmt.setString(++i, status);

			final ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ListDesignatePathResponse(rs.getInt("id"), rs.getString("status"), rs.getString("country"),
						rs.getString("province"), rs.getString("city"), rs.getString("township"),
						rs.getString("village"), rs.getString("street"), rs.getString("name")));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_ITEM = "SELECT COUNT(*) AS c FROM designate_path ";

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
