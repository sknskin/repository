package com.examplesweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// HttpServlet을 상속받은 클래스는 서블릿 클래스
public class HelloServlet extends HttpServlet {
	
	// HTTP GET 요청이 발생하면 호출되는 메서드
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter(); // 클라이언트에게 응답을 전송하는 처리기
		out.println("Hello, Servlet Response !!!!! " + new Date());
		
	}
	// HTTP POST 요청이 발생하면 호출되는 메서드
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}









