package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.ICategoryService;

public class CategoryService implements ICategoryService {
	
	@Inject
	private ICategoryDAO categoryDAO;
	

	@Override
	public Integer getTotalItems() {
		return categoryDAO.getTotalItems();
	}

	@Override
	public CategoryModel findOne(Long id) {
		return categoryDAO.findOne(id);
	}
	
	@Override
	public List<CategoryModel> findAll(Pageable pageable) {
		return categoryDAO.findAll(pageable);
	}

	@Override
	public CategoryModel save(CategoryModel categoryModel) {
		categoryModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		
		Long id = categoryDAO.save(categoryModel);

		return categoryDAO.findOne(id);
	}

	@Override
	public CategoryModel update(CategoryModel categoryModel) {
		CategoryModel oldCategory = categoryDAO.findOne(categoryModel.getId());

		categoryModel.setCreatedDate(oldCategory.getCreatedDate());
		categoryModel.setCreatedBy(oldCategory.getCreatedBy());
		categoryModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		
		categoryDAO.update(categoryModel);

		return categoryDAO.findOne(categoryModel.getId());
	}

	@Override
	public void delete(long[] ids) {
		for (long id: ids) {
			categoryDAO.delete(id);
		}
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		
		return categoryDAO.findOneByCode(code);
	}

}
