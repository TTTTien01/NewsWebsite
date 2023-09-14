package com.laptrinhjavaweb.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {

	public static SessionUtil sessionUtil;

	//getInstance(): kiểm tra đối tượng có tồn tại chưa. 
	//-Nếu tồn tại rồi thì return lun.
	//-Chưa thì khởi tạo.
	public static SessionUtil getInstance() {
		if (sessionUtil == null) {
			sessionUtil = new SessionUtil();
		}

		return sessionUtil;
	}
 
	//setAttribute để Put giá trị vào Sesstion
	public void putValue(HttpServletRequest request, String key, Object value) {
		request.getSession().setAttribute(key, value);
	}

	//khi chưa biết kiểu dữ liệu sử dụng Object
	//Object: ép kiểu -> kiểu dữ liệu mong muốn
	//Dựa vào key để get giá trị ra
	public Object getValue(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
	}

	//HttpServletRequest request: để khởi tạo Sesstion
	//Dựa vào key để xóa 
	public void removeValue(HttpServletRequest request, String key) {
		request.getSession().removeAttribute(key);
	}
}
