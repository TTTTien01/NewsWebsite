package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.CommentModel;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.ICommentService;
import com.laptrinhjavaweb.service.INewsService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.MessageUtil;

// 1. lấy tất cả comment
// 2. kiểm duyệt comment (ko hợp lệ => .invalid = true/false)
// 3. xóa comment

@WebServlet(urlPatterns = { "/admin-comments" })
public class CommentController extends HttpServlet {

	@Inject
	private ICommentService commentService;
	
	@Inject
	private IUserService userService;
	
	@Inject
	private INewsService newsService;

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CommentModel model = FormUtil.toModel(CommentModel.class, request);
		String view = "";
		if (model.getType() != null && model.getType().equals(SystemConstant.LIST)) {
			view = "/views/admin/comment/list.jsp";
			Pageable pageable = new PageRequest(model.getPage(), model.getMaxPageItems(),
					new Sorter(model.getSortName(), model.getSortBy()));

			model.setListResult(commentService.findAll(pageable));

			model.setTotalItems(commentService.getTotalItems());
			model.setTotalPages((int) Math.ceil((double) model.getTotalItems() / (double) model.getMaxPageItems()));
			
		} else if (model.getType() != null && model.getType().equals(SystemConstant.EDIT)) {
			if (model.getId() != null) {
				model = commentService.findOne(model.getId());
			}

			view = "/views/admin/comment/edit.jsp";
		}
		MessageUtil.showMessage(request);
		request.setAttribute(SystemConstant.MODEL, model);
		
		// load users list
		List<UserModel> users = userService.findAll(null);
		
		// load news list
		List<NewsModel> news = newsService.findAll(null);
		
		// set attributes users and news
		request.setAttribute("users", users);
		request.setAttribute("news", news);
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
}