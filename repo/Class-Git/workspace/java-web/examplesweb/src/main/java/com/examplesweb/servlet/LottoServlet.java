package com.examplesweb.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/select-lotto-numbers"})
public class LottoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 요청 처리 (번호 뽑기)
		int[] numbers = new int[6];
		for (int i = 0; i < 6; i = i + 1) {
			numbers[i] = (int) (Math.random() * 45) + 1; // 1 ~ 45
			for (int j = 0; j < i; j = j + 1) {
				if (numbers[i] == numbers[j]) {
					// i = i - 1;//i의 위치를 감소시켜서 증감식의 증가 효과를 제거
					i = -1; // 처음부터 다시 뽑기
				}
			}
		}
		
		// 2. JSP에서 사용하도록 request에 데이터 저장
		req.setAttribute("numbers", numbers); // int[] or String or int or ....
		
		// 3. JSP로 이동
		RequestDispatcher dispatcher = req.getRequestDispatcher("lotto.jsp");
		dispatcher.forward(req, resp);
	}
	
}




