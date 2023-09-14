package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.ContactModel;
import com.laptrinhjavaweb.paging.Pageable;

public interface IContactService {
	
	int getTotalItems();
	
	List<ContactModel> findAll(Pageable pageable);
	
	ContactModel findOne(Long id);

	ContactModel save(ContactModel model);

	ContactModel update(ContactModel model);
	
	void delete(long[] ids);
}
