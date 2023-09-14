package com.laptrinhjavaweb.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUtil {
	@SuppressWarnings("unchecked")
	public static <T> T toModel(Class<T> tClass, HttpServletRequest request) {
		
		T object = null;
		
		try {
			//Khởi tạo đối tượng
			object = tClass.newInstance();
			//Dựa vào key=value 
			BeanUtils.populate(object, request.getParameterMap());
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			System.out.println(e);
		}
		
		return object;
	}
}
