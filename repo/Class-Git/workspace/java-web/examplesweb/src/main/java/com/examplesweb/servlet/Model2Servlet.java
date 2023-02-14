package com.examplesweb.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/model2" })
public class Model2Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 1. 요청 처리
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분 ss초");
		String dateString = sdf.format(new Date());
		
		// JSP에서 읽을 수 있도록 데이터 전달 ( request 객체에 저장 )
		req.setAttribute("date", dateString);
		
		// 2. 응답 컨텐츠 생산 ( JSP로 forward 이동 )		
		RequestDispatcher rd = req.getRequestDispatcher("04.model2.jsp");
		rd.forward(req, resp); // 지정된 경로로 forward 이동
	}
	
}










