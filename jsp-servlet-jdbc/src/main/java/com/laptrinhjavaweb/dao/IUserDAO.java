package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.paging.Pageable;

public interface IUserDAO extends GenericDAO<UserModel> {

	UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);

	Integer getTotalItems();

	UserModel findOne(Long id);

	List<UserModel> findAll(Pageable pageable);

	Long save(UserModel model);

	void update(UserModel model);

	void delete(long id);
}
