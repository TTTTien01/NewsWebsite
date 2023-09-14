package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.paging.Pageable;

public interface IUserService {

	Integer getTotalItems();

	UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);

	UserModel findOne(Long id);

	List<UserModel> findAll(Pageable pageable);

	UserModel save(UserModel model);

	UserModel update(UserModel model);

	void delete(long[] ids);
}
