package com.laptrinhjavaweb.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetUtil {

	// Hàm static để kiểm tra xem có tồn tại cột "name" trong ResultSet hay không
	public static boolean hasColumn(ResultSet resultSet, String columnName) {
		try {
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				String currentColumnName = metaData.getColumnName(i);
				if (currentColumnName.equalsIgnoreCase(columnName)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
