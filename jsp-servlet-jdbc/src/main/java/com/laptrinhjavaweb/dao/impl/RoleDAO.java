package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.dao.IRoleDAO;
import com.laptrinhjavaweb.mapper.RoleMapper;
import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.paging.Pageable;

public class RoleDAO extends AbstractDAO<RoleModel> implements IRoleDAO {

	@Override
	public Integer getTotalItems() {
		String sql = "SELECT count(*) from role";
		return count(sql);
	}

	@Override
	public RoleModel findOneByCode(String code) {
		String sql = "SELECT * FROM role WHERE code = ?";

		List<RoleModel> roles = query(sql, new RoleMapper(), code);

		return roles.isEmpty() ? null : roles.get(0);
	}

	@Override
	public RoleModel findOne(Long id) {
		String sql = "SELECT * FROM role WHERE id = ?";

		List<RoleModel> roles = query(sql, new RoleMapper(), id);

		return roles.isEmpty() ? null : roles.get(0);
	}

	@Override
	public List<RoleModel> findAll(Pageable pageable) {
		StringBuilder sql = new StringBuilder("SELECT * FROM role");

		if (pageable != null) {
			if (pageable.getSoter() != null && StringUtils.isNotBlank(pageable.getSoter().getSortName())
					&& StringUtils.isNotBlank(pageable.getSoter().getSortBy())) {
				sql.append(" ORDER BY " + pageable.getSoter().getSortName() + " " + pageable.getSoter().getSortBy());
			}

			if (pageable.getOffset() != null && pageable.getLimit() != null) {
				sql.append(" LIMIT " + pageable.getOffset() + ", " + pageable.getLimit());
			}
		}

		return query(sql.toString(), new RoleMapper());
	}

	@Override
	public Long save(RoleModel model) {
		StringBuilder sql = new StringBuilder("INSERT INTO role (name, code,");
		sql.append(" createddate, createdby)");
		sql.append(" VALUES (?, ?, ?, ?)");

		return insert(sql.toString(), model.getName(), model.getCode(), model.getCreatedDate(), model.getCreatedBy());
	}

	@Override
	public void update(RoleModel model) {
		StringBuilder sql = new StringBuilder("UPDATE role SET name = ?, code = ?,");
		sql.append(" createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ?");
		sql.append(" WHERE id = ?");

		update(sql.toString(), model.getName(), model.getCode(), model.getCreatedDate(), model.getCreatedBy(),
				model.getModifiedDate(), model.getModifiedBy(), model.getId());
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM role WHERE id = ?";
		update(sql, id);
	}

}
