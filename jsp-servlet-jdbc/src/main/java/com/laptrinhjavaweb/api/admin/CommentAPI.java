package com.laptrinhjavaweb.api.admin;

import java.io.IOException;

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

@WebServlet(urlPatterns = "/api-admin-comments")
public class CommentAPI extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICommentService commentService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		// lấy thông tin người đăng nhập hiện tại
		UserModel currentUser = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		
		// nhận comment từ client và save
		CommentModel model = HttpUtil.of(request.getReader()).toModel(CommentModel.class);
		model.setCreatedBy(currentUser.getUserName());
		model = commentService.save(model); // lưu model => nhận về model mới tạo
		
		mapper.writeValue(response.getOutputStream(), model); // trả về client
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		// chuyển json => json string => model
		CommentModel model = HttpUtil.of(request.getReader()).toModel(CommentModel.class);
		model.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		model = commentService.update(model); // lưu model mới => nhận về model mới tạo
		mapper.writeValue(response.getOutputStream(), model); // trả về model mới tạo
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		// chuyển json => json string => model
		CommentModel model = HttpUtil.of(request.getReader()).toModel(CommentModel.class);
		commentService.delete(model.getIds()); // xóa model theo nhiều id
		mapper.writeValue(response.getOutputStream(), ""); // xóa nên trả về rỗng
	}

}
