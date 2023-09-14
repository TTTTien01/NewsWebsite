package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.IRoleDAO;
import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.IRoleService;

public class RoleService implements IRoleService {
	
	@Inject
	private IRoleDAO roleDAO;

	@Override
	public Integer getTotalItems() {
		return roleDAO.getTotalItems();
	}

	@Override
	public RoleModel findOne(Long id) {
		return roleDAO.findOne(id);
	}
	
	@Override
	public List<RoleModel> findAll(Pageable pageable) {
		return roleDAO.findAll(pageable);
	}

	@Override
	public RoleModel save(RoleModel model) {
		model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		
		Long id = roleDAO.save(model);

		return roleDAO.findOne(id);
	}

	@Override
	public RoleModel update(RoleModel model) {
		RoleModel oldModel = roleDAO.findOne(model.getId());

		model.setCreatedDate(oldModel.getCreatedDate());
		model.setCreatedBy(oldModel.getCreatedBy());
		model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		
		roleDAO.update(model);

		return roleDAO.findOne(model.getId());
	}

	@Override
	public void delete(long[] ids) {
		for (long id: ids) {
			roleDAO.delete(id);
		}
	}

}
