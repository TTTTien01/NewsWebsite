package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.NewsModel;

public interface ISearchService {
	List<NewsModel> searchByTitle(String title);
}
