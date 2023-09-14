package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.laptrinhjavaweb.dao.GenericDAO;
import com.laptrinhjavaweb.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {

	//kết nối dữ liệ từ file db.properties
	ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

	public Connection getConnection() {
		try {
			Class.forName(resourceBundle.getString("driverName"));
			String url = resourceBundle.getString("url");
			String userName = resourceBundle.getString("user");
			String password = resourceBundle.getString("pass");

			return DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void setParameters(PreparedStatement statement, Object... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				int index = i + 1; // vì index của câu truy vấn sql bắt đầu từ 1
				Object parameter = parameters[i];
				if (parameter instanceof Long) {
					statement.setLong(index, (Long) parameter);
				} else if (parameter instanceof String) {
					statement.setString(index, parameter.toString());
				} else if (parameter instanceof Integer) {
					statement.setInt(index, (Integer) parameter);
				} else if (parameter instanceof Timestamp) {
					statement.setTimestamp(index, (Timestamp) parameter);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Hàm đếm page
	@Override
	public int count(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			int count = 0;
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			setParameters(statement, parameters);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

			return count;
		} catch (SQLException e) {
			return 0;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}

				if (statement != null) {
					statement.close();
				}

				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				return 0;
			}
		}
	}

	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> results = new ArrayList<T>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			setParameters(statement, parameters);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}

			return results;
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}

				if (statement != null) {
					statement.close();
				}

				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				return null;
			}
		}
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Long id = null;
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		Connection connection = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);

			statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
			setParameters(statement, parameters);

			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			connection.commit();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			return id;
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}

				if (statement != null) {
					statement.close();
				}

				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				return null;
			}
		}
	}

	@Override
	public void update(String sql, Object... parameters) {
		PreparedStatement statement = null;
		Connection connection = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);

			statement = connection.prepareStatement(sql);
			setParameters(statement, parameters);

			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}

				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

}
