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
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.IRoleService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.MessageUtil;

@WebServlet(urlPatterns = { "/admin-accounts" })
public class AccountController extends HttpServlet {

	@Inject
	private IUserService userService;
	
	@Inject
	private IRoleService roleService;

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel model = FormUtil.toModel(UserModel.class, request);
		String view = "";
		if (model.getType() != null && model.getType().equals(SystemConstant.LIST)) {
			view = "/views/admin/account/list.jsp";
			Pageable pageable = new PageRequest(model.getPage(), model.getMaxPageItems(),
					new Sorter(model.getSortName(), model.getSortBy()));

			model.setListResult(userService.findAll(pageable));
			model.setTotalItems(userService.getTotalItems());
			model.setTotalPages((int) Math.ceil((double) model.getTotalItems() / (double) model.getMaxPageItems()));
			
		} else if (model.getType() != null && model.getType().equals(SystemConstant.EDIT)) {
			if (model.getId() != null) {
				model = userService.findOne(model.getId());
			}
			
			request.setAttribute("roles", roleService.findAll(null));
			view = "/views/admin/account/edit.jsp";
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