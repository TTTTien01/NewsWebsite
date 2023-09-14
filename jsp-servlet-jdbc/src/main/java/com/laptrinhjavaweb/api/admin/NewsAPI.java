package com.laptrinhjavaweb.api.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.INewsService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = { "/api-admin-news" })
public class NewsAPI extends HttpServlet {

	@Inject
	private INewsService newsService;

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// chuyển json => json string => news model
		NewsModel newsModel = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
		newsModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		newsModel = newsService.save(newsModel); // lưu news model => nhận về news mới tạo
		mapper.writeValue(response.getOutputStream(), newsModel); // trả về news mới tạo
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// chuyển json => json string => news model
		NewsModel updateNews = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
		updateNews.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		updateNews = newsService.update(updateNews); // lưu news model mới => nhận về news mới tạo
		mapper.writeValue(response.getOutputStream(), updateNews); // trả về news mới tạo
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// chuyển json => json string => news model
		NewsModel deleteNews = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
		newsService.delete(deleteNews.getIds()); // xóa news theo nhiều id
		mapper.writeValue(response.getOutputStream(), ""); // xóa nên trả về rỗng
	}
}
