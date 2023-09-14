package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.RoleModel;

public class RoleMapper implements RowMapper<RoleModel> {

	@Override
	public RoleModel mapRow(ResultSet resultSet) {
		RoleModel role = new RoleModel();
		try {
			role.setId(resultSet.getLong("id"));
			role.setCode(resultSet.getString("code"));
			role.setName(resultSet.getString("name"));

			role.setCreatedDate(resultSet.getTimestamp("createddate"));
			role.setCreatedBy(resultSet.getString("createdby"));

			if (resultSet.getTimestamp("modifieddate") != null) {
				role.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}

			if (resultSet.getString("modifiedby") != null) {
				role.setModifiedBy(resultSet.getString("modifiedby"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return role;
	}

}
