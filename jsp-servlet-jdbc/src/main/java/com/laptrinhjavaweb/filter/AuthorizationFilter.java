package com.laptrinhjavaweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.utils.SessionUtil;

public class AuthorizationFilter implements Filter {

	private ServletContext context;
	
	//lấy filter lên
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
	}

	//
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		//Lấy thông tin url vd: /trang-chu
		String url = request.getRequestURI();
		
		//Kiểm tra các url có /admin
		if (url.startsWith("/admin")) {
			//Gọi lại model . -> Lưu tất cả thông tin người dùng vào SessionUtil
			UserModel model = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
			
			if (model != null) {
				if (model.getRole().getCode().equals(SystemConstant.ADMIN)) {
					//Dùng cho những url không cần filter
					filterChain.doFilter(servletRequest, servletResponse);
				} else if (model.getRole().getCode().equals(SystemConstant.USER)) {
					//Nếu là USER không có quyền đăng nhập 
					response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=not_permission&alert=danger");
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=not_login&alert=danger");
			}
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
