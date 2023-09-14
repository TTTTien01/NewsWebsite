package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.paging.Pageable;

public interface IRoleDAO extends GenericDAO<RoleModel> {
	
	Integer getTotalItems();
	
	RoleModel findOne(Long id);
	
	RoleModel findOneByCode(String code);
	
	List<RoleModel> findAll(Pageable pageable);
	
	Long save(RoleModel model);

	void update(RoleModel model);
	
	void delete(long id);
}
