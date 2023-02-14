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

import com.demoweb.dto.MemberDto;
import com.demoweb.service.AccountService;

@WebServlet(urlPatterns = { "/account/register.action" })
public class RegisterServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 요청 처리
		//2. JSP가 읽을 수 있도록 request에 데이터 저장
		//3. jsp로 forward 이동
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/account/register.jsp");
		rd.forward(req, resp); // 지정된 경로로 forward 이동
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//1-1. 요청 데이터 읽기
		String memberId = req.getParameter("memberId");
		String passwd = req.getParameter("passwd");
		String confirm = req.getParameter("confirm");
		String email = req.getParameter("email");
		
		System.out.printf("[%s][%s][%s][%s]\n", memberId, passwd, confirm, email);
		
		// 데이터베이스에 데이터 저장 ( JDBC -> Service에게 요청 )
		MemberDto memberDto = new MemberDto();
		memberDto.setMemberId(memberId);
		memberDto.setPasswd(passwd);
		memberDto.setEmail(email);
		AccountService accountService = new AccountService();
		accountService.registerMember(memberDto);
		
		
		// 한 작업 사이클이 종료되면 redirect로 이동
		resp.sendRedirect("/demoweb/account/login.action");
		
	}

}








