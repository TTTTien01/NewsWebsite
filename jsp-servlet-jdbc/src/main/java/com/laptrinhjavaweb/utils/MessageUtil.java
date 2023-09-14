package com.laptrinhjavaweb.utils;

import javax.servlet.http.HttpServletRequest;

public class MessageUtil {
	
	public static void showMessage(HttpServletRequest request) {
		if (request.getParameter("message") != null) {
			String messageResponse = "";
			String alert = "";
			String message = request.getParameter("message");
			if (message.equals("insert_success")) {
				messageResponse = "Thêm thành công!";
				alert = "success";
			} else if (message.equals("update_success")) {
				messageResponse = "Cập nhật thành công!";
				alert = "success";
			} else if (message.equals("delete_success")) {
				messageResponse = "Xóa thành công!";
				alert = "success";
			} else if (message.equals("error_system")) {
				messageResponse = "Có lỗi!";
				alert = "danger";
			}
			request.setAttribute("messageResponse", messageResponse);
			request.setAttribute("alert", alert);
		}
	}
}
