package com.laptrinhjavaweb.api.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.IRoleService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = { "/api-admin-roles" })
public class RoleAPI extends HttpServlet {

	@Inject
	private IRoleService roleService;

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// chuyển json => json string => model
		RoleModel roleModel = HttpUtil.of(request.getReader()).toModel(RoleModel.class);
		roleModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		roleModel = roleService.save(roleModel); // lưu model => nhận về model mới tạo
		mapper.writeValue(response.getOutputStream(), roleModel); // trả về model mới tạo
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// chuyển json => json string => model
		RoleModel roleModel = HttpUtil.of(request.getReader()).toModel(RoleModel.class);
		roleModel.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
		roleModel = roleService.update(roleModel); // lưu model mới => nhận về model mới tạo
		mapper.writeValue(response.getOutputStream(), roleModel); // trả về model mới tạo
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// chuyển json => json string => model
		RoleModel deleteModel = HttpUtil.of(request.getReader()).toModel(RoleModel.class);
		roleService.delete(deleteModel.getIds()); // xóa theo nhiều id
		mapper.writeValue(response.getOutputStream(), ""); // xóa nên trả về rỗng
	}
}