package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.mapper.CategoryMapper;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.paging.Pageable;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

	@Override
	public Integer getTotalItems() {
		String sql = "SELECT count(*) from category";
		return count(sql);
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		String sql = "SELECT * FROM category WHERE code = ?";

		List<CategoryModel> categories = query(sql, new CategoryMapper(), code);

		return categories.isEmpty() ? null : categories.get(0);
	}

	@Override
	public CategoryModel findOne(Long id) {
		String sql = "SELECT * FROM category WHERE id = ?";

		List<CategoryModel> categories = query(sql, new CategoryMapper(), id);

		return categories.isEmpty() ? null : categories.get(0);
	}

	@Override
	public List<CategoryModel> findAll(Pageable pageable) {
		StringBuilder sql = new StringBuilder("SELECT * FROM category");

		if (pageable != null) {
			if (pageable.getSoter() != null && StringUtils.isNotBlank(pageable.getSoter().getSortName())
					&& StringUtils.isNotBlank(pageable.getSoter().getSortBy())) {
				sql.append(" ORDER BY " + pageable.getSoter().getSortName() + " " + pageable.getSoter().getSortBy());
			}

			if (pageable.getOffset() != null && pageable.getLimit() != null) {
				sql.append(" LIMIT " + pageable.getOffset() + ", " + pageable.getLimit());
			}
		}

		return query(sql.toString(), new CategoryMapper());
	}

	@Override
	public Long save(CategoryModel category) {
		StringBuilder sql = new StringBuilder("INSERT INTO category (name, code,");
		sql.append(" createddate, createdby)");
		sql.append(" VALUES (?, ?, ?, ?)");

		return insert(sql.toString(), category.getName(), category.getCode(), category.getCreatedDate(),
				category.getCreatedBy());
	}

	@Override
	public void update(CategoryModel category) {
		StringBuilder sql = new StringBuilder("UPDATE category SET name = ?, code = ?,");
		sql.append(" createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ?");
		sql.append(" WHERE id = ?");

		update(sql.toString(), category.getName(), category.getCode(), category.getCreatedDate(),
				category.getCreatedBy(), category.getModifiedDate(), category.getModifiedBy(), category.getId());
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM category WHERE id = ?";
		update(sql, id);
	}

}
