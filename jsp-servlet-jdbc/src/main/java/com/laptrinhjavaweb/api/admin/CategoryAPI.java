package com.laptrinhjavaweb.api.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = { "/api-admin-categories" })
public class CategoryAPI extends HttpServlet {

	@Inject
	private ICategoryService categoryService;

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// chuyển json => json string => model
		CategoryModel categoryModel = HttpUtil.of(request.getReader()).toModel(CategoryModel.class);
		categoryModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		categoryModel = categoryService.save(categoryModel); // lưu model => nhận về model mới tạo
		mapper.writeValue(response.getOutputStream(), categoryModel); // trả về model mới tạo
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// chuyển json => json string => model
		CategoryModel categoryModel = HttpUtil.of(request.getReader()).toModel(CategoryModel.class);
		categoryModel.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		categoryModel = categoryService.update(categoryModel); // lưu model mới => nhận về model mới tạo
		mapper.writeValue(response.getOutputStream(), categoryModel); // trả về model mới tạo
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// chuyển json => json string => model
		CategoryModel deleteModel = HttpUtil.of(request.getReader()).toModel(CategoryModel.class);
		categoryService.delete(deleteModel.getIds()); // xóa theo nhiều id
		mapper.writeValue(response.getOutputStream(), ""); // xóa nên trả về rỗng
	}
}
