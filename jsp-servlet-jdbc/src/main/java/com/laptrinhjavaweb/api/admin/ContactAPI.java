package com.laptrinhjavaweb.api.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.ContactModel;
import com.laptrinhjavaweb.service.IContactService;
import com.laptrinhjavaweb.utils.HttpUtil;

@WebServlet(urlPatterns = "/api-admin-contacts")
public class ContactAPI extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IContactService contactService;

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		// chuyển json => json string => model
		ContactModel model = HttpUtil.of(request.getReader()).toModel(ContactModel.class);
		contactService.delete(model.getIds()); // xóa model theo nhiều id
		mapper.writeValue(response.getOutputStream(), ""); // xóa nên trả về rỗng
	}

}
