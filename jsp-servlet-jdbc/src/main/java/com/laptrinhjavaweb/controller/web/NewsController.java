package com.laptrinhjavaweb.controller.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.CommentModel;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.ICommentService;
import com.laptrinhjavaweb.service.INewsService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;

@WebServlet(urlPatterns = { "/tin-tuc" })
public class NewsController extends HttpServlet {

	@Inject
	private INewsService newsService;

	@Inject
	private ICategoryService categoryService;

	@Inject
	private ICommentService commentService;

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		NewsModel model = (NewsModel) FormUtil.toModel(NewsModel.class, request);

		Pageable pageable = new PageRequest(model.getPage(), model.getMaxPageItems(),
				new Sorter(model.getSortName(), model.getSortBy()));

		String view = "";
		// lấy danh sách tin tức theo categoryCode
		if (model.getCategoryCode() != null) {
			model.setListResult(newsService.findByCategoryCode(model.getCategoryCode(), pageable));

			model.setTotalItems(newsService.getTotalItemsByCategoryCode(model.getCategoryCode()));
			model.setTotalPages(
					(int) Math.ceil((double) newsService.getTotalItemsByCategoryCode(model.getCategoryCode())
							/ (double) model.getMaxPageItems()));

			request.setAttribute("currentCaterogyCode", model.getCategoryCode());
			request.setAttribute("categoryName", (categoryService.findOneByCode(model.getCategoryCode())).getName());
			request.setAttribute("listByCategory", true);
			view = "/views/web/news/list.jsp";

			// lấy chi tiết tin tức
		} else if (model.getId() != null) {
			model = newsService.findOne(model.getId());

			// set comments của tin tức được lấy
			List<CommentModel> comments = commentService.findByNewsId(model.getId());
			request.setAttribute("comments", comments);

			view = "/views/web/news/details.jsp";

			
		} else if (model.getSearch() != null) {
			model.setListResult(newsService.findBySearchString(model.getSearch()));
			
			request.setAttribute("listByCategory", false);
			
			view = "/views/web/news/list.jsp";
		// lấy toàn bộ tin tức
		} else {
			model.setListResult(newsService.findAll(pageable));

			model.setTotalItems(newsService.getTotalItems());
			model.setTotalPages((int) Math.ceil((double) model.getTotalItems() / (double) model.getMaxPageItems()));

			request.setAttribute("listByCategory", false);

			view = "/views/web/news/list.jsp";
		}

		request.setAttribute("model", model);
		request.setAttribute("categories", categoryService.findAll(null));
		request.setAttribute("contextPath", request.getContextPath() + File.separator + "images");
		RequestDispatcher rq = request.getRequestDispatcher(view);
		rq.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
