package com.laptrinhjavaweb.api.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.service.INewsService;

@WebServlet(urlPatterns = "/news-views-increment")
public class NewsAPI extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private INewsService newsService;

	// /news-views-increment?id=1
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		Long newsId = Long.parseLong(request.getParameter("id"));
		NewsModel model = newsService.increaseViewsById(newsId);
		
		mapper.writeValue(response.getOutputStream(), model);
	}
}
