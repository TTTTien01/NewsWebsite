package com.laptrinhjavaweb.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	private String value; // json string

	public HttpUtil(String value) {
		this.value = value;
	}

	// json string => model
	public <T> T toModel(Class<T> tClass) {
		try {
			return new ObjectMapper().readValue(value, tClass);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	// json => json string
	public static HttpUtil of(BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return new HttpUtil(sb.toString());
	}
}
