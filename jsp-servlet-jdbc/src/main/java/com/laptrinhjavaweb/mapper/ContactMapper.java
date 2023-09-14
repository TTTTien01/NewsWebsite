package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.ContactModel;

public class ContactMapper implements RowMapper<ContactModel> {

	@Override
	public ContactModel mapRow(ResultSet resultSet) {
		ContactModel model = new ContactModel();
		try {
			model.setId(resultSet.getLong("id"));
			model.setFullName(resultSet.getString("fullname"));
			model.setEmail(resultSet.getString("email"));
			model.setPhoneNumber(resultSet.getString("phonenumber"));
			model.setTitle(resultSet.getString("title"));
			model.setContent(resultSet.getString("content"));

			model.setCreatedDate(resultSet.getTimestamp("createddate"));
			model.setCreatedBy(resultSet.getString("createdby"));
			if (resultSet.getTimestamp("modifieddate") != null) {
				model.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}
			if (resultSet.getString("modifiedby") != null) {
				model.setModifiedBy(resultSet.getString("modifiedby"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return model;
	}

}
