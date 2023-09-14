package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.IRoleService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.MessageUtil;

@WebServlet(urlPatterns = { "/admin-roles" })
public class RoleController extends HttpServlet {

	@Inject
	private IRoleService roleService;

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RoleModel model = FormUtil.toModel(RoleModel.class, request);
		String view = "";
		if (model.getType() != null && model.getType().equals(SystemConstant.LIST)) {
			view = "/views/admin/role/list.jsp";
			Pageable pageable = new PageRequest(model.getPage(), model.getMaxPageItems(),
					new Sorter(model.getSortName(), model.getSortBy()));

			model.setListResult(roleService.findAll(pageable));

			model.setTotalItems(roleService.getTotalItems());
			model.setTotalPages((int) Math.ceil((double) model.getTotalItems() / (double) model.getMaxPageItems()));
			
		} else if (model.getType() != null && model.getType().equals(SystemConstant.EDIT)) {
			if (model.getId() != null) {
				model = roleService.findOne(model.getId());
			}

			view = "/views/admin/role/edit.jsp";
		}
		MessageUtil.showMessage(request);
		request.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}