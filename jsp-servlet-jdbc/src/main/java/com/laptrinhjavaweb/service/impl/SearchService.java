package com.laptrinhjavaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.impl.NewsDAO;
import com.laptrinhjavaweb.model.NewsModel;

public class SearchService {
	
    private NewsDAO newsDAO = new NewsDAO();
	
    public List<NewsModel> searchByTitle(String title) {
        return newsDAO.searchByTitle(title);
    }
}
