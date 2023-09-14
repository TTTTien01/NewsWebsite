package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.mapper.RowMapper;

public interface GenericDAO<T> {

	int count(String sql, Object... parameters);

	//Hiển thị danh sách
	<T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);

	Long insert(String sql, Object... parameters);

	void update(String sql, Object... parameters);
}
