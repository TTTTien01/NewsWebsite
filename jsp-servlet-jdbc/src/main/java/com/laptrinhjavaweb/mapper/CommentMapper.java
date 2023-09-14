package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.CommentModel;

public class CommentMapper implements RowMapper<CommentModel> {

	@Override
	public CommentModel mapRow(ResultSet resultSet) {
		CommentModel comment = new CommentModel();
		try {
			comment.setId(resultSet.getLong("id"));
			comment.setContent(resultSet.getString("content"));
			comment.setUserId(resultSet.getLong("user_id"));
			comment.setNewsId(resultSet.getLong("new_id"));
			comment.setCreatedBy(resultSet.getString("createdby"));
			comment.setCreatedDate(resultSet.getTimestamp("createddate"));
			comment.setInvalid(resultSet.getInt("invalid"));

			if (resultSet.getTimestamp("modifieddate") != null) {
				comment.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}

			if (resultSet.getString("modifiedby") != null) {
				comment.setModifiedBy(resultSet.getString("modifiedby"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comment;
	}
}
