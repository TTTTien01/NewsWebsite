package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.IContactDAO;
import com.laptrinhjavaweb.model.ContactModel;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.IContactService;

public class ContactService implements IContactService {

	@Inject
	private IContactDAO contactDAO;
	
	@Override
	public int getTotalItems() {
		
		return contactDAO.getTotalItems();
	}

	@Override
	public List<ContactModel> findAll(Pageable pageable) {
		
		return contactDAO.findAll(pageable);
	}

	@Override
	public ContactModel findOne(Long id) {
		
		return contactDAO.findOne(id);
	}

	@Override
	public ContactModel save(ContactModel model) {
		
		model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		
		Long id = contactDAO.save(model);
		
		return contactDAO.findOne(id);
	}

	@Override
	public ContactModel update(ContactModel model) {
		ContactModel oldContact = contactDAO.findOne(model.getId());

		model.setCreatedDate(oldContact.getCreatedDate());
		model.setCreatedBy(oldContact.getCreatedBy());
		model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		
		contactDAO.update(model);

		return contactDAO.findOne(model.getId());
	}

	@Override
	public void delete(long[] ids) {
		
		for (long id: ids) {
			// 1. delete comment
			// ...
			// 2. delete news
			contactDAO.delete(id);
		}
	}

}
