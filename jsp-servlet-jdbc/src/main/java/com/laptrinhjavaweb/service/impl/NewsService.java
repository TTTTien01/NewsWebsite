package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.INewsService;

public class NewsService implements INewsService {

	@Inject
	private INewsDAO newsDAO;

	@Inject
	private ICategoryDAO categoryDAO;

	@Override
	public int getTotalItems() {
		return newsDAO.getTotalItems();
	}

	@Override
	public int getTotalItemsByCategoryCode(String categoryCode) {

		CategoryModel categoryModel = categoryDAO.findOneByCode(categoryCode);

		return newsDAO.getTotalItemsByCategoryId(categoryModel.getId());
	}

	@Override
	public NewsModel findOne(Long id) {
		NewsModel news = newsDAO.findOne(id);

		CategoryModel category = categoryDAO.findOne(news.getCategoryId());

		news.setCategoryCode(category.getCode());

		return news;
	}

	@Override
	public List<NewsModel> findAll(Pageable pageable) {
		return newsDAO.findAll(pageable);
	}

	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {
		return newsDAO.findByCategoryId(categoryId);
	}

	@Override
	public List<NewsModel> findByCategoryCode(String categoryCode) {
		CategoryModel category = categoryDAO.findOneByCode(categoryCode);

		return newsDAO.findByCategoryId(category.getId());
	}

	@Override
	public List<NewsModel> findByCategoryCode(String categoryCode, Pageable pageable) {
		CategoryModel category = categoryDAO.findOneByCode(categoryCode);

		return newsDAO.findByCategoryId(category.getId(), pageable);
	}

	@Override
	public NewsModel save(NewsModel news) {
		//
		news.setCreatedDate(new Timestamp(System.currentTimeMillis()));

		if (news.getThumbnail().isEmpty()) {
			// TODO: set default thumb nail
			news.setThumbnail("default-image.jpg");
		}

		// tìm category theo code
		CategoryModel categoryModel = categoryDAO.findOneByCode(news.getCategoryCode());

		// set categoryId cho news
		news.setCategoryId(categoryModel.getId());

		Long id = newsDAO.save(news);

		return newsDAO.findOne(id);
	}

	@Override
	public NewsModel update(NewsModel updateNews) {
		NewsModel oldNews = newsDAO.findOne(updateNews.getId());

		updateNews.setCreatedDate(oldNews.getCreatedDate());
		updateNews.setCreatedBy(oldNews.getCreatedBy());
		updateNews.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		if (updateNews.getThumbnail().isEmpty()) {
			updateNews.setThumbnail(oldNews.getThumbnail());
		}

		// tìm category theo code
		CategoryModel categoryModel = categoryDAO.findOneByCode(updateNews.getCategoryCode());

		// set categoryId cho news
		updateNews.setCategoryId(categoryModel.getId());

		newsDAO.update(updateNews);

		return newsDAO.findOne(updateNews.getId());
	}

	@Override
	public void delete(long[] ids) {
		for (long id : ids) {
			// 1. delete comment
			// ...
			// 2. delete news
			newsDAO.delete(id);
		}
	}

	@Override
	public List<NewsModel> findBySearchString(String search) {

		return newsDAO.findBySearchString(search);
	}

	@Override
	public NewsModel increaseViewsById(Long id) {
		NewsModel model = newsDAO.findOne(id);

		model.setViews(model.getViews() + 1);

		newsDAO.update(model);

		return model;
	}

	@Override
	public List<NewsModel> findPopularNews() {
		
		return newsDAO.findPopularNews();
	}

	@Override
	public List<NewsModel> findAllOrderByCreatedDateAsc() {
		// TODO Auto-generated method stub
		return newsDAO.findAllOrderByCreatedDateAsc();
	}

}
