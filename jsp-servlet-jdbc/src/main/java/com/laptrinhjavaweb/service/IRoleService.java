package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.paging.Pageable;

public interface IRoleService {

	Integer getTotalItems();
	
	RoleModel findOne(Long id);
	
	List<RoleModel> findAll(Pageable pageable);
	
	RoleModel save(RoleModel model);
	
	RoleModel update(RoleModel model);
	
	void delete(long[] ids);
}
