package com.side.servlet;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServeletContext {
	private static final ThreadLocal<HttpServletRequest> request;
	private static final ThreadLocal<HttpServletResponse> response;
	static {
		request = new ThreadLocal<HttpServletRequest>();
		response = new ThreadLocal<HttpServletResponse>();
	}

	public static void setRequest(ServletRequest req, ServletResponse res) {
		request.set((HttpServletRequest) req);
		response.set((HttpServletResponse) res);
	}

	public static HttpServletRequest getRequest() {
		return request.get();
	}

	public static HttpServletResponse getResponse() {
		return response.get();
	}

}
