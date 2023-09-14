package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.utils.ResultSetUtil;

public class UserMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet resultSet) {
		UserModel user = new UserModel();
		try {
			user.setId(resultSet.getLong("id"));
			user.setUserName(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setFullName(resultSet.getString("fullname"));
			user.setStatus(resultSet.getInt("status"));
			user.setRoleId(resultSet.getLong("roleid"));

			user.setCreatedDate(resultSet.getTimestamp("createddate"));
			user.setCreatedBy(resultSet.getString("createdby"));
			if (resultSet.getTimestamp("modifieddate") != null) {
				user.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}
			if (resultSet.getString("modifiedby") != null) {
				user.setModifiedBy(resultSet.getString("modifiedby"));
			}

			if (ResultSetUtil.hasColumn(resultSet, "name") && StringUtils.isNotBlank(resultSet.getString("name"))) {
				//Trường hợp nếu sử dụng câu truy vấn không inner join
				try {
					RoleModel role = new RoleModel();
					role.setName(resultSet.getString("name"));
					role.setCode(resultSet.getString("code"));
					user.setRole(role);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

}
