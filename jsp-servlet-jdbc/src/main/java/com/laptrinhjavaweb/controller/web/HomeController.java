package com.laptrinhjavaweb.controller.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewsService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = { "/trang-chu", "/dang-nhap", "/dang-ky", "/thoat" })
public class HomeController extends HttpServlet {

	@Inject
	private IUserService userService;

	@Inject
	private ICategoryService categoryService;

	@Inject
	private INewsService newsService;

	private static final long serialVersionUID = 1L;

	private ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null && action.equals("login")) {
			
			String message = request.getParameter("message");
			String alert = request.getParameter("alert");
			if (message != null && alert != null) {
				//truyền từ controller ra JSP sd setAttribute
				request.setAttribute("message", resourceBundle.getString(message));
				request.setAttribute("alert", alert);
			}

			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		} else if (action != null && action.equals("signup")) {
			String message = request.getParameter("message");
			String alert = request.getParameter("alert");
			if (message != null && alert != null) {
				request.setAttribute("message", resourceBundle.getString(message));
				request.setAttribute("alert", alert);
			}

			RequestDispatcher rd = request.getRequestDispatcher("/views/signup.jsp");
			rd.forward(request, response);
			
			//Thoát
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "USERMODEL");
			response.sendRedirect(request.getContextPath() + "/trang-chu");
		// trang chủ
		} else {
			List<NewsModel> popularNews = newsService.findPopularNews();
			List<NewsModel> lastestNews = newsService.findAllOrderByCreatedDateAsc();
			request.setAttribute("popularNews", popularNews.subList(0, 6));
			request.setAttribute("lastestNews", lastestNews.subList(0, 6));
			request.setAttribute("contextPath", request.getContextPath() + File.separator + "images");
			request.setAttribute("categories", categoryService.findAll(null));
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if (action != null && action.equals("login")) {
			//Lấy được userName và password
			UserModel model = FormUtil.toModel(UserModel.class, request);
			model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(), model.getPassword(), 1);
			//Kiểm tra Authen...
			if (model != null) {
				//getInstance(): kiểm tra đối tượng có tồn tại chưa. 
				//-Nếu tồn tại rồi thì return lun.
				//-Chưa thì khởi tạo.
				SessionUtil.getInstance().putValue(request, "USERMODEL", model);
				if (model.getRole().getCode().equals("USER")) {
					response.sendRedirect(request.getContextPath() + "/trang-chu");
				//Autho.. kiểm tra quyền admin
				} else if (model.getRole().getCode().equals("ADMIN")) {
					response.sendRedirect(request.getContextPath() + "/admin-home");
				}
			} else {
				//redirect đến controler- Thông báo lỗi
				response.sendRedirect(request.getContextPath()
						+ "/dang-nhap?action=login&message=invalid_username_password&alert=danger");
			}
		} else if (action != null && action.equals("signup")) {
			UserModel model = FormUtil.toModel(UserModel.class, request);
			model = userService.save(model);
			if (model != null) {
				response.sendRedirect(
						request.getContextPath() + "/dang-nhap?action=login&message=signup_successful&alert=success");
			} else {
				response.sendRedirect(
						request.getContextPath() + "/dang-ky?action=signup&message=signup_failed&alert=danger");
			}
		}
	}
}
