package com.demoweb.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/home.action" })
public class HomeServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 요청 처리
		
		// 2. JSP에서 읽을 수 있도록 데이터 전달 ( request 객체에 저장 )
		
		// 3. 응답 컨텐츠 생산 ( JSP로 forward 이동 )		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/home.jsp");
		rd.forward(req, resp); // 지정된 경로로 forward 이동
	}

}
