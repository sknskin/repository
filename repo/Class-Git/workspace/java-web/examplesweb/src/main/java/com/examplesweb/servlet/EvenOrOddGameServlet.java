package com.examplesweb.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/even-or-odd-game" })
public class EvenOrOddGameServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 1-1. 사용자 입력 데이터 읽기
		String sUserValue = req.getParameter("even-or-odd");// 비교 : reg.getAttribute
		int userValue = Integer.parseInt(sUserValue);
		// 1-2. 컴퓨터 데이터 생성
		int comValue = (int)(Math.random() * 2) + 1;
		// 1-3. 승패 결정
		String result2 = (userValue == comValue) ? "WIN" : "LOSE";
		String result = null;
		if (userValue == comValue) {
			result = "WIN";
		} else { 
			result = "LOSE";
		}
		
		// 2. JSP에서 사용하도록 request에 데이터 저장 ( 여러 개 저장 가능 )
		req.setAttribute("userValue", userValue);
		req.setAttribute("comValue", comValue);
		req.setAttribute("result", result);
		
		// 3. JSP로 이동
		RequestDispatcher dispatcher = req.getRequestDispatcher("even-odd-game.jsp");
		dispatcher.forward(req, resp);
		
	}

}









