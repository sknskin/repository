package com.examplesweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = { "*.jsp" })
public class ExamplesWebFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;		
		System.out.println("요청 처리 중 : " + req.getRequestURI()); // 현재 요청 경로 출력
		
		chain.doFilter(request, response); // 다음 필터 또는 JSP로 전달
		
		
	}

}












