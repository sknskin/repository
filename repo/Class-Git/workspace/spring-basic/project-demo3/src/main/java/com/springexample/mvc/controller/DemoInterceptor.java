package com.springexample.mvc.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

public class DemoInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// Controller에서는 redirectAttributes.addFlashAttribute("b", new Date())
		FlashMap flashMap = new FlashMap();
		flashMap.put("b", new Date()); 
		
		FlashMapManager manager = RequestContextUtils.getFlashMapManager(request);
		manager.saveOutputFlashMap(flashMap, request, response);
		
		response.sendRedirect("redirect-target");
		
		return false; // 컨트롤러로 가지 마세요
	}

}








