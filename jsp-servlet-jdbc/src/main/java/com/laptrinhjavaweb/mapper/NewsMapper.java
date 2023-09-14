package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.model.NewsModel;

public class NewsMapper implements RowMapper<NewsModel> {

	@Override
	public NewsModel mapRow(ResultSet resultSet) {
		NewsModel news = new NewsModel();
		try {
			news.setId(resultSet.getLong("id"));
			news.setTitle(resultSet.getString("title"));
			news.setThumbnail(resultSet.getString("thumbnail"));
			news.setShortDescription(resultSet.getString("shortdescription"));
			news.setContent(resultSet.getString("content"));
			news.setCategoryId(resultSet.getLong("categoryid"));
			news.setCreatedDate(resultSet.getTimestamp("createddate"));
			news.setCreatedBy(resultSet.getString("createdby"));
			news.setViews(resultSet.getLong("views"));
			
			if (StringUtils.isNotBlank(resultSet.getString("name"))
					|| StringUtils.isNotEmpty(resultSet.getString("name"))) {
				news.setCategoryName(resultSet.getString("name"));
			}
			if (StringUtils.isNotBlank(resultSet.getString("code"))
					|| StringUtils.isNotEmpty(resultSet.getString("code"))) {
				news.setCategoryCode(resultSet.getString("code"));
			}

			if (resultSet.getTimestamp("modifieddate") != null) {
				news.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}

			if (resultSet.getString("modifiedby") != null) {
				news.setModifiedBy(resultSet.getString("modifiedby"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return news;
	}

}
