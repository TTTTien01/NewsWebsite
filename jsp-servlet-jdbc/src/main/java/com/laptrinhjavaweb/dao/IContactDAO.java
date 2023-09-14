package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.ContactModel;
import com.laptrinhjavaweb.paging.Pageable;

public interface IContactDAO {
	
	int getTotalItems();
	
	List<ContactModel> findAll(Pageable pageable);
	
	ContactModel findOne(Long id);

	Long save(ContactModel model);

	void update(ContactModel model);
	
	void delete(long id);
}
