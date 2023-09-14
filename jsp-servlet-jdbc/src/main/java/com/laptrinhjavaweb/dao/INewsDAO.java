package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageable;

public interface INewsDAO extends GenericDAO<NewsModel> {
	
	int getTotalItems();
	
	int getTotalItemsByCategoryId(Long categoryId);
	
	List<NewsModel> findAll(Pageable pageable);
	
	NewsModel findOne(Long id);
	
	List<NewsModel> findByCategoryId(Long categoryId);
	
	List<NewsModel> findByCategoryId(Long categoryId, Pageable pageable);
	
	List<NewsModel> findBySearchString(String search);

	Long save(NewsModel news);

	void update(NewsModel news);
	
	void delete(long id);

	List<NewsModel> findPopularNews();

	List<NewsModel> findAllOrderByCreatedDateAsc();
}
