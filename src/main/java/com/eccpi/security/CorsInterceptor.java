package com.eccpi.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CorsInterceptor extends HandlerInterceptorAdapter {

	 @Override
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		 response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	        // added: Authorization to list of allowed headers in order to make HMAC authorization work in cross domain
	        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Origin, Accept, Content-Type, Authorization");
	        response.setHeader("Access-Control-Max-Age", "1800"); //30 min
	  return true;
	 }

	}
