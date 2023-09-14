package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.paging.Pageable;

public interface ICategoryDAO extends GenericDAO<CategoryModel> {
	
	Integer getTotalItems();
	
	CategoryModel findOne(Long id);
	
	CategoryModel findOneByCode(String code);
	
	//Lấy hết Dl từ DB
	List<CategoryModel> findAll(Pageable pageable);
	
	Long save(CategoryModel model);

	void update(CategoryModel model);
	
	void delete(long id);
}
