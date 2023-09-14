package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageable;

public interface INewsService {

	int getTotalItems();
	
	int getTotalItemsByCategoryCode(String categoryCode);

	NewsModel findOne(Long id);
	
	List<NewsModel> findAll(Pageable pageable);

	List<NewsModel> findByCategoryId(Long categoryId);
	
	List<NewsModel> findByCategoryCode(String categoryCode);

	List<NewsModel> findByCategoryCode(String categoryCode, Pageable pageable);
	
	List<NewsModel> findBySearchString(String search);
	
	List<NewsModel> findPopularNews();
	
	List<NewsModel> findAllOrderByCreatedDateAsc();
	
	NewsModel save(NewsModel news);

	NewsModel update(NewsModel updateNews);

	void delete(long[] ids);
	
	NewsModel increaseViewsById(Long id);
}
