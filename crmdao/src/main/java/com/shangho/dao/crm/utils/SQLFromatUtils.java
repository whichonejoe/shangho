package com.shangho.dao.crm.utils;

import java.util.List;

public class SQLFromatUtils {
	public static String formatWhereDescription(int i, final String sqlWhereDescription, String description) {
		if (i == 0) {
			description += " WHERE " + sqlWhereDescription;
		} else {
			description += " AND " + sqlWhereDescription;
		}
		return description;
	}

	/**
	 * 處理 SQL like敘述
	 * 
	 * @param list
	 * @return
	 */
	public static String handleSQLLikeStatement(final List<String> list, final String value) {
		String statement = "";
		final String likeStatement = " LIKE '%@%' ";
		final String orStatement = " OR ";
		final int index = list.size() - 1;
		for (int i = 0; i <= index; i++) {
			statement += value + likeStatement.replace("@", list.get(i));
			if (i == index)
				break;
			else
				statement += orStatement;
		}
		return statement;
	}

	/**
	 * 處理 SQL like敘述
	 * 
	 * @param list
	 * @return
	 */
	public static String handleSQLLikeStatementWithInt(final List<Integer> list, final String value) {
		String statement = "";
		final String likeStatement = " LIKE '%@%' ";
		final String orStatement = " OR ";
		final int index = list.size() - 1;
		for (int i = 0; i <= index; i++) {
			statement += value + likeStatement.replace("@", list.get(i).toString());
			if (i == index)
				break;
			else
				statement += orStatement;
		}
		return statement;
	}
}
