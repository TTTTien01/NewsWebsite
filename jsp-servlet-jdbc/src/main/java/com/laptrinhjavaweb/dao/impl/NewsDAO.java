package com.laptrinhjavaweb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.mapper.NewsMapper;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageable;

public class NewsDAO extends AbstractDAO<NewsModel> implements INewsDAO {

	@Override
	public int getTotalItems() {
		String sql = "SELECT count(*) FROM news";
		return count(sql);
	}
	
	@Override
	public int getTotalItemsByCategoryId(Long categoryId) {
		String sql = "SELECT count(*) FROM news WHERE categoryid=?";
		
		return count(sql, categoryId);
	}

	@Override
	public List<NewsModel> findAll(Pageable pageable) {
		StringBuilder sql = new StringBuilder("SELECT n.*, c.name, c.code FROM news n JOIN category c ON n.categoryid = c.id");

		if (pageable != null) {
			if (pageable.getSoter() != null && StringUtils.isNotBlank(pageable.getSoter().getSortName())
					&& StringUtils.isNotBlank(pageable.getSoter().getSortBy())) {
				sql.append(" ORDER BY " + pageable.getSoter().getSortName() + " " + pageable.getSoter().getSortBy());
			}

			if (pageable.getOffset() != null && pageable.getLimit() != null) {
				sql.append(" LIMIT " + pageable.getOffset() + ", " + pageable.getLimit());
			}
		}

		return query(sql.toString(), new NewsMapper());
	}

	@Override
	public NewsModel findOne(Long id) {
		String sql = "SELECT n.*, c.name, c.code FROM news n JOIN category c ON n.categoryid = c.id WHERE n.id=?";

		List<NewsModel> newsList = query(sql, new NewsMapper(), id);

		return newsList.isEmpty() ? null : newsList.get(0);
	}

	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {
		String sql = "SELECT n.*, c.name, c.code FROM news n JOIN category c ON n.categoryid = c.id WHERE c.id=?";

		return query(sql, new NewsMapper(), categoryId);
	}
	
	@Override
	public List<NewsModel> findBySearchString(String search) {
		StringBuilder sql = new StringBuilder("SELECT n.*, c.name, c.code FROM news n JOIN category c ON n.categoryid = c.id");
		sql.append(" WHERE n.title or n.shortdescription LIKE ?");
		
		return query(sql.toString(), new NewsMapper(), "%" + search + "%");
	}
	
	@Override
	public List<NewsModel> findByCategoryId(Long categoryId, Pageable pageable) {
		StringBuilder sql = new StringBuilder("SELECT n.*, c.name, c.code FROM news n JOIN category c ON n.categoryid = c.id WHERE c.id=?");

		if (pageable.getSoter() != null && StringUtils.isNotBlank(pageable.getSoter().getSortName())
				&& StringUtils.isNotBlank(pageable.getSoter().getSortBy())) {
			sql.append(" ORDER BY " + pageable.getSoter().getSortName() + " " + pageable.getSoter().getSortBy());
		}

		if (pageable.getOffset() != null && pageable.getLimit() != null) {
			sql.append(" LIMIT " + pageable.getOffset() + ", " + pageable.getLimit());
		}

		return query(sql.toString(), new NewsMapper(), categoryId);
	}

	@Override
	public Long save(NewsModel news) {
		StringBuilder sql = new StringBuilder("INSERT INTO news (title, thumbnail, shortdescription,");
		sql.append(" content, categoryid, createddate, createdby, views)");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

		return insert(sql.toString(), news.getTitle(), news.getThumbnail(), news.getShortDescription(),
				news.getContent(), news.getCategoryId(), news.getCreatedDate(), news.getCreatedBy(), 0);
	}

	@Override
	public void update(NewsModel news) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?, shortdescription = ?,");
		sql.append(" content = ?, categoryid = ?, createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ?, views = ?");
		sql.append(" WHERE id = ?");

		update(sql.toString(), news.getTitle(), news.getThumbnail(), news.getShortDescription(), news.getContent(),
				news.getCategoryId(), news.getCreatedDate(), news.getCreatedBy(), news.getModifiedDate(),
				news.getModifiedBy(), news.getViews(), news.getId());
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		update(sql, id);
	}

	public List<NewsModel> searchByTitle(String title) {
		List<NewsModel> results = new ArrayList<>();
	    
	    StringBuilder sql = new StringBuilder("SELECT * FROM news WHERE title LIKE ?");
	    String searchString = "%" + title + "%";
	    sql.append(searchString);
		return results;

	}

	@Override
	public List<NewsModel> findPopularNews() {
		StringBuilder sql = new StringBuilder("SELECT n.*, c.name, c.code FROM news n JOIN category c ON n.categoryid = c.id");
		sql.append(" ORDER BY n.views DESC");
		
		return query(sql.toString(), new NewsMapper());
	}

	@Override
	public List<NewsModel> findAllOrderByCreatedDateAsc() {
		StringBuilder sql = new StringBuilder("SELECT n.*, c.name, c.code FROM news n JOIN category c ON n.categoryid = c.id");
		sql.append(" ORDER BY n.createddate DESC");
		
		return query(sql.toString(), new NewsMapper());
	}

}
