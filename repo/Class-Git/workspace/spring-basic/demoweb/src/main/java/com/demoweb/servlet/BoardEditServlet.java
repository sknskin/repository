package com.demoweb.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demoweb.dto.BoardDto;
import com.demoweb.service.BoardService;

@WebServlet(urlPatterns = { "/board/edit.action" })
public class BoardEditServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 요청 처리
		String sBoardNo = req.getParameter("boardNo"); // 요청 데이터 읽기 : 사용자가 선택한 글번호 읽기
		int boardNo = Integer.parseInt(sBoardNo);
		String sPageNo = req.getParameter("pageNo");
		int pageNo = Integer.parseInt(sPageNo);
	
		BoardService boardService = new BoardService();
		
		BoardDto board = boardService.findBoardByBoardNo(boardNo);
		
		if (board == null) { // 조회되지 않은 경우 (글 번호가 잘못되었거나 또는 삭제된 글인 경우)
			resp.sendRedirect("list.action");
			return;
		}
		
		// 2. JSP에서 읽을 수 있도록 데이터 전달 ( request 객체에 저장 )
		req.setAttribute("board", board);
		req.setAttribute("pageNo", pageNo);
		
		// 3. 응답 컨텐츠 생산 ( JSP로 forward 이동 )		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/board/edit.jsp");
		rd.forward(req, resp); // 지정된 경로로 forward 이동
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 요청 데이터 읽기
		req.setCharacterEncoding("utf-8");
		
		String sBoardNo = req.getParameter("boardNo");
		int boardNo = Integer.parseInt(sBoardNo);
		String sPageNo = req.getParameter("pageNo");
		int pageNo = Integer.parseInt(sPageNo);
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		BoardDto board = new BoardDto();
		board.setBoardNo(boardNo);
		board.setTitle(title);
		board.setContent(content);
		
		// 데이터 처리
		BoardService boardService = new BoardService();
		boardService.modifyBoard(board);
		
		resp.sendRedirect("detail.action?boardNo=" + boardNo + "&pageNo=" + pageNo);
	}

}


















