package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.paging.Pageable;

public interface ICategoryService {
	
	Integer getTotalItems();
	
	CategoryModel findOne(Long id);
	
	CategoryModel findOneByCode(String code);
	
	List<CategoryModel> findAll(Pageable pageable);
	
	CategoryModel save(CategoryModel categoryModel);
	
	CategoryModel update(CategoryModel categoryModel);
	
	void delete(long[] ids);
}
