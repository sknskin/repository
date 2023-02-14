package com.demoweb.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.demoweb.common.Util;
import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardDto;
import com.demoweb.dto.MemberDto;
import com.demoweb.service.BoardService;

@WebServlet(urlPatterns = { "/board/write.action" })
public class BoardWriteServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인하지 않은 사용자는 로그인 화면으로 보내기 ( 필터에서 구현하므로 주석 처리 )
//		HttpSession session = req.getSession();
//		MemberDto member = (MemberDto)session.getAttribute("loginuser");
//		if (member == null) { // 세션에 로그인 데이터 없음 -> 로그인하지 않은 사용자
//			resp.sendRedirect("/demoweb/account/login.action");
//			return;
//		}
		
		// 1. 요청 처리		
		
		// 2. JSP에서 읽을 수 있도록 데이터 전달 ( request 객체에 저장 )		
		// 3. 응답 컨텐츠 생산 ( JSP로 forward 이동 )		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/board/write.jsp");
		rd.forward(req, resp); // 지정된 경로로 forward 이동
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");

		//파일 업로드를 포함한 요청인지 확인 (multipart/form-data 형식 확인)
		if (ServletFileUpload.isMultipartContent(req) == false) {
			resp.sendRedirect("list.action");
			return;
		}

		//경로 문자열을 저장할 변수
		//application.getRealPath('웹경로')
		//--> 가상경로(웹경로) -> 물리경로(컴퓨터경로)
		//--> http://..... -> C:/......
		ServletContext application = req.getServletContext(); // JSP의 application 내장 객체
		String path = application.getRealPath("/board-attachments");	//최종 파일 저장 경로
		String tempPath = application.getRealPath("/board-temp");		//임시 파일 저장 경로

		//전송 데이터 각 요소를 분리해서 개별 객체를 만들때 사용할 처리기
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024 * 2);	//임시 파일을 만들지 결정하는 기준 설정
		factory.setRepository(new File(tempPath));	//임시 파일 저장 경로 지정

		//요청 정보를 읽을 파서(Parser) 생성 (요청을 읽고 요소별로 분리)
		ServletFileUpload uploader = new ServletFileUpload(factory);
		uploader.setFileSizeMax(1024 * 1024 * 10);//최대 파일 크기

		BoardDto board = new BoardDto();	// 게시글 정보를 저장하는 DTO 객체
		ArrayList<BoardAttachDto> attachments = new ArrayList<>(); // 첨부파일 정보를 저장하는 DTO 객체
		board.setAttachments(attachments);
		
		//요청 정보를 파싱하고 개별 객체의 목록을 반환
		try {
			List<FileItem> items = uploader.parseRequest(req);
			
			//목록에 담긴 데이터 사용
			for (FileItem item : items) {			
				if (item.isFormField()) { //form-data인 경우 (File이 아닌 일반 데이터인 경우)
					if (item.getFieldName().equals("title")) {
						board.setTitle(item.getString("utf-8"));
					} else if (item.getFieldName().equals("writer")) {
						board.setWriter(item.getString("utf-8"));
					} else if (item.getFieldName().equals("content")) {
						board.setContent(item.getString("utf-8"));
					}					
				} else { //file인 경우
					String fileName = item.getName(); //파일 이름 가져오기
					if (fileName != null && fileName.length() > 0) { //내용이 있는 경우
						if (fileName.contains("\\")) { // iexplore 경우
							//C:\AAA\BBB\CCC.png -> CCC.png 
							fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
						}
						String uniqueFileName = Util.makeUniqueFileName(fileName);
						try {
							//item.write(new File(path, fileName));//파일 저장
							item.write(new File(path, uniqueFileName));//파일 저장
						} catch (Exception e) {
							e.printStackTrace();
						} 
						item.delete(); //임시 파일 삭제
						
						// 첨부파일 정보를 객체에 저장
						BoardAttachDto attachment = new BoardAttachDto();
						attachment.setUserFileName(fileName);
						attachment.setSavedFileName(uniqueFileName);
						attachments.add(attachment);
					}
				}
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		
		BoardService boardService = new BoardService();
		boardService.writeBoard(board);
		
		resp.sendRedirect("list.action");
		
	}

}













