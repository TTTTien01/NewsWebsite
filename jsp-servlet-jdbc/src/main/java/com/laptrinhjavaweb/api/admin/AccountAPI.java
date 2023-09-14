package com.laptrinhjavaweb.api.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = { "/api-admin-accounts" })
public class AccountAPI extends HttpServlet {

	@Inject
	private IUserService userService;

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// chuyển json => json string => model
		UserModel userModel = HttpUtil.of(request.getReader()).toModel(UserModel.class);
		userModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		userModel = userService.save(userModel); // lưu model => nhận về model mới tạo
		mapper.writeValue(response.getOutputStream(), userModel); // trả về model mới tạo
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// chuyển json => json string => model
		UserModel userModel = HttpUtil.of(request.getReader()).toModel(UserModel.class);
		userModel.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		userModel = userService.update(userModel); // lưu model mới => nhận về model mới tạo
		mapper.writeValue(response.getOutputStream(), userModel); // trả về model mới tạo
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// chuyển json => json string => model
		UserModel deleteModel = HttpUtil.of(request.getReader()).toModel(UserModel.class);
		userService.delete(deleteModel.getIds()); // xóa theo nhiều id
		mapper.writeValue(response.getOutputStream(), ""); // xóa nên trả về rỗng
	}
}