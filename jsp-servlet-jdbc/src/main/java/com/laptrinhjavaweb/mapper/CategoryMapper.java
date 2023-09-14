package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel> {

	@Override
	public CategoryModel mapRow(ResultSet resultSet) {
		CategoryModel category = new CategoryModel();
		try {
			category.setId(resultSet.getLong("id"));
			category.setCode(resultSet.getString("code"));
			category.setName(resultSet.getString("name"));
			
			category.setCreatedDate(resultSet.getTimestamp("createddate"));
			category.setCreatedBy(resultSet.getString("createdby"));

			if (resultSet.getTimestamp("modifieddate") != null) {
				category.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}

			if (resultSet.getString("modifiedby") != null) {
				category.setModifiedBy(resultSet.getString("modifiedby"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return category;
	}

}
