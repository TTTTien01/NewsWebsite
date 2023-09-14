package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.mapper.UserMapper;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.paging.Pageable;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT u.*, r.name, r.code");
		sql.append(" FROM user AS u");
		sql.append(" INNER JOIN role AS r ON u.roleid = r.id");
		sql.append(" WHERE username = ? AND password = ? AND status = ?");

		List<UserModel> users = query(sql.toString(), new UserMapper(), userName, password, status);
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public Integer getTotalItems() {
		String sql = "SELECT count(*) from user";
		return count(sql);
	}

	@Override
	public UserModel findOne(Long id) {
		String sql = "SELECT * FROM user WHERE id = ?";

		List<UserModel> users = query(sql, new UserMapper(), id);

		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public List<UserModel> findAll(Pageable pageable) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user");

		if (pageable != null) {
			if (pageable.getSoter() != null && StringUtils.isNotBlank(pageable.getSoter().getSortName())
					&& StringUtils.isNotBlank(pageable.getSoter().getSortBy())) {
				sql.append(" ORDER BY " + pageable.getSoter().getSortName() + " " + pageable.getSoter().getSortBy());
			}

			if (pageable.getOffset() != null && pageable.getLimit() != null) {
				sql.append(" LIMIT " + pageable.getOffset() + ", " + pageable.getLimit());
			}
		}

		return query(sql.toString(), new UserMapper());
	}

	@Override
	public Long save(UserModel model) {
		StringBuilder sql = new StringBuilder("INSERT INTO user (username, password, fullname, status, roleid,");
		sql.append(" createddate, createdby)");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?)");

		return insert(sql.toString(), model.getUserName(), model.getPassword(), model.getFullName(), model.getStatus(),
				model.getRoleId(), model.getCreatedDate(), model.getCreatedBy());
	}

	@Override
	public void update(UserModel model) {
		StringBuilder sql = new StringBuilder("UPDATE user SET username = ?, password = ?,");
		sql.append(" fullname = ?, status = ?, roleid = ?,");
		sql.append(" createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ?");
		sql.append(" WHERE id = ?");

		update(sql.toString(), model.getUserName(), model.getPassword(), model.getFullName(), model.getStatus(),
				model.getRoleId(), model.getCreatedDate(), model.getCreatedBy(), model.getModifiedDate(),
				model.getModifiedBy(), model.getId());
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM user WHERE id = ?";
		update(sql, id);
	}

}
