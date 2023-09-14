package com.laptrinhjavaweb.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.ContactModel;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.IContactService;
import com.laptrinhjavaweb.utils.FormUtil;

// hiển thị form và thêm liên hệ
@WebServlet(urlPatterns = "/lien-he")
public class ContactController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private ICategoryService categoryService;

	@Inject
	private IContactService contactService;

	// load theo categories để hiển thị trên header
	// request.setAttribute("categories", ...)
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("categories", categoryService.findAll(null));
		RequestDispatcher rd = request.getRequestDispatcher("/views/web/contact/form.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		ContactModel model = FormUtil.toModel(ContactModel.class, request);
		model.setCreatedBy(model.getEmail());
		model = contactService.save(model);

		if (model != null) {
			request.setAttribute("alert", "success");
			request.setAttribute("message", "Gửi liên hệ thành công!");
		} else {
			request.setAttribute("alert", "danger");
			request.setAttribute("message", "Lỗi hệ thống! Gửi liên hệ thất bại.");
		}
		
		request.setAttribute("categories", categoryService.findAll(null));
		RequestDispatcher rd = request.getRequestDispatcher("/views/web/contact/form.jsp");
		rd.forward(request, response);
	}
}
