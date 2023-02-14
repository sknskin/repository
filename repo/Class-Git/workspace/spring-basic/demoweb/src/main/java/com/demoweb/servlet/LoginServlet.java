package com.demoweb.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demoweb.dto.MemberDto;
import com.demoweb.service.AccountService;

@WebServlet(urlPatterns = { "/account/login.action" })
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 요청 처리
		//2. JSP가 읽을 수 있도록 request에 데이터 저장
		//3. jsp로 forward 이동
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/account/login.jsp");
		rd.forward(req, resp); // 지정된 경로로 forward 이동
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8");
		
		//1-1. 요청 데이터 읽기
		String memberId = req.getParameter("memberId");
		String passwd = req.getParameter("passwd");

		System.out.printf("[%s][%s]\n", memberId, passwd);
		
		// 데이터베이스에서 데이터 조회 ( Dao에 요청 )
		AccountService accountService = new AccountService();
		MemberDto memberDto = accountService.findMemberByIdAndPasswd(memberId, passwd);
		
		if (memberDto != null) {
			// System.out.println("로그인 성공");
			// 로그인 처리 --> Session에 데이터 저장
			HttpSession session = req.getSession(); // 세션 가져오기
			session.setAttribute("loginuser", memberDto);
			
			// 한 작업 사이클이 종료되면 redirect로 이동
			resp.sendRedirect("/demoweb/home.action");
		} else {
			System.out.println("로그인 실패");
			// 한 작업 사이클이 종료되면 redirect로 이동
			resp.sendRedirect("/demoweb/account/login.action");
		}
		
		
	}

}








