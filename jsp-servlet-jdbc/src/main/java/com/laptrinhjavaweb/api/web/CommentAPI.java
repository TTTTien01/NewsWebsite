package com.laptrinhjavaweb.api.web;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.CommentModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.ICommentService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = "/api-comments")
public class CommentAPI extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICommentService commentService;

	// /api-comments?newsid=1
	// lấy comments theo id của bài viết
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		Long id = Long.parseLong(request.getParameter("newsid"));
		
		List<CommentModel> comments = commentService.findByNewsId(id);
		
		mapper.writeValue(response.getOutputStream(), comments); // trả về client
	}
	
	// /api-comments?newsid=1
	// đăng comment
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		// lấy thông tin người đăng nhập hiện tại
		UserModel currentUser = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		
		Long newsId = Long.parseLong(request.getParameter("newsid"));
		
		// nhận comment từ client và save
		CommentModel model = HttpUtil.of(request.getReader()).toModel(CommentModel.class);
		model.setNewsId(newsId);
		model.setUserId(currentUser.getId());
		model.setCreatedBy(currentUser.getUserName());
		model = commentService.save(model); // lưu news model => nhận về news mới tạo
		
		mapper.writeValue(response.getOutputStream(), model); // trả về client
	}
}
