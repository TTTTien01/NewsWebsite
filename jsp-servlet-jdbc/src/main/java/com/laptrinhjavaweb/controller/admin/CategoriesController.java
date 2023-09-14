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
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.MessageUtil;

@WebServlet(urlPatterns = { "/admin-categories" })
public class CategoriesController extends HttpServlet {

	@Inject
	private ICategoryService categoryService;

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryModel model = FormUtil.toModel(CategoryModel.class, request);
		String view = "";
		if (model.getType() != null && model.getType().equals(SystemConstant.LIST)) {
			view = "/views/admin/category/list.jsp";
			Pageable pageable = new PageRequest(model.getPage(), model.getMaxPageItems(),
					new Sorter(model.getSortName(), model.getSortBy()));

			model.setListResult(categoryService.findAll(pageable));

			model.setTotalItems(categoryService.getTotalItems());
			model.setTotalPages((int) Math.ceil((double) model.getTotalItems() / (double) model.getMaxPageItems()));
			
		} else if (model.getType() != null && model.getType().equals(SystemConstant.EDIT)) {
			if (model.getId() != null) {
				model = categoryService.findOne(model.getId());
			}

			view = "/views/admin/category/edit.jsp";
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