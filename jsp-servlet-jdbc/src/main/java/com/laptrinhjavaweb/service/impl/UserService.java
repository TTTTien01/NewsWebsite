package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.IRoleDAO;
import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.IUserService;

public class UserService implements IUserService {

	@Inject
	private IUserDAO userDAO;
	
	@Inject
	private IRoleDAO roleDAO;

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		UserModel model = userDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
		
		if (model != null) {
			RoleModel role = roleDAO.findOne(model.getRoleId());
			
			model.setRoleCode(role.getCode());
		}
		
		return model;
	}

	@Override
	public Integer getTotalItems() {
		return userDAO.getTotalItems();
	}

	@Override
	public UserModel findOne(Long id) {
		
		UserModel user = userDAO.findOne(id);
		
		RoleModel role = roleDAO.findOne(user.getRoleId());
		
		user.setRoleCode(role.getCode());
		
		return user;
	}

	@Override
	public List<UserModel> findAll(Pageable pageable) {
		return userDAO.findAll(pageable);
	}

	@Override
	public UserModel save(UserModel model) {
		RoleModel role;
		// nếu role code != null => tạo bởi admin
		if (model.getRoleCode() != null) {			
			role = roleDAO.findOneByCode(model.getRoleCode());
		// tạo bởi người dùng khi đăng ký
		} else {
			model.setStatus(1);
			model.setCreatedBy(model.getUserName());
			role = roleDAO.findOneByCode("USER");
		}
		
		model.setRoleId(role.getId());
		model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		
		Long id = userDAO.save(model);
		if (id != null) {
			return userDAO.findOne(id);
		}

		return null;
	}

	@Override
	public UserModel update(UserModel model) {
		RoleModel role = roleDAO.findOneByCode(model.getRoleCode());
		
		model.setRoleId(role.getId());
		
		UserModel oldModel = userDAO.findOne(model.getId());

		model.setCreatedDate(oldModel.getCreatedDate());
		model.setCreatedBy(oldModel.getCreatedBy());
		model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		
		userDAO.update(model);

		return userDAO.findOne(model.getId());
	}

	@Override
	public void delete(long[] ids) {
		for (long id: ids) {
			userDAO.delete(id);
		}
	}

}
