package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.dao.IContactDAO;
import com.laptrinhjavaweb.mapper.ContactMapper;
import com.laptrinhjavaweb.model.ContactModel;
import com.laptrinhjavaweb.paging.Pageable;

public class ContactDAO extends AbstractDAO<ContactModel> implements IContactDAO {

	@Override
	public int getTotalItems() {

		return count("SELECT count(*) FROM contact");
	}

	@Override
	public List<com.laptrinhjavaweb.model.ContactModel> findAll(Pageable pageable) {
		StringBuilder sql = new StringBuilder("SELECT * FROM contact");

		if (pageable != null) {
			if (pageable.getSoter() != null && StringUtils.isNotBlank(pageable.getSoter().getSortName())
					&& StringUtils.isNotBlank(pageable.getSoter().getSortBy())) {
				sql.append(" ORDER BY " + pageable.getSoter().getSortName() + " " + pageable.getSoter().getSortBy());
			}

			if (pageable.getOffset() != null && pageable.getLimit() != null) {
				sql.append(" LIMIT " + pageable.getOffset() + ", " + pageable.getLimit());
			}
		}

		return query(sql.toString(), new ContactMapper());
	}

	@Override
	public com.laptrinhjavaweb.model.ContactModel findOne(Long id) {

		String sql = "SELECT * FROM contact WHERE id = ?";

		List<ContactModel> contacts = query(sql, new ContactMapper(), id);

		return contacts.isEmpty() ? null : contacts.get(0);
	}

	@Override
	public Long save(ContactModel model) {

		StringBuilder sql = new StringBuilder("INSERT INTO contact (fullname, email, phonenumber,");
		sql.append(" title, content, createddate, createdby)");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?)");

		return insert(sql.toString(), model.getFullName(), model.getEmail(), model.getPhoneNumber(), model.getTitle(),
				model.getContent(), model.getCreatedDate(), model.getCreatedBy());
	}

	@Override
	public void update(ContactModel model) {

		StringBuilder sql = new StringBuilder("UPDATE contact SET fullname = ?, email = ?, phonenumber = ?,");
		sql.append(" title = ?, content = ?, createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ?");
		sql.append(" WHERE id = ?");

		update(sql.toString(), model.getFullName(), model.getEmail(), model.getPhoneNumber(), model.getTitle(),
				model.getContent(), model.getCreatedDate(), model.getCreatedBy(), model.getModifiedDate(),
				model.getModifiedBy(), model.getId());

	}

	@Override
	public void delete(long id) {
		
		String sql = "DELETE FROM contact WHERE id = ?";
		
		update(sql, id);
	}

}
