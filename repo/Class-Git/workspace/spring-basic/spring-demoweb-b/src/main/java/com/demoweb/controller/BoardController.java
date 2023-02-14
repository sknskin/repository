package com.demoweb.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;

import com.demoweb.common.Util;
import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardDto;
import com.demoweb.service.BoardService;
import com.demoweb.service.BoardServiceImpl;
import com.demoweb.ui.ThePager;
import com.demoweb.view.DownloadView;

@Controller
@RequestMapping(path = { "/board" })
public class BoardController {
	
	private final int PAGE_SIZE = 3; 	// 한 페이지에 표시되는 데이터 개수
	private final int PAGER_SIZE = 3;	// 한 번에 표시할 페이지 번호 개수
	private final String LINK_URL = "list.action"; // 페이지 번호를 클릭했을 때 이동할 페이지 경로

	@Autowired
	@Qualifier("boardService")
	private BoardService boardService;
	
	@GetMapping(path = { "/list.action" })
	public String showBoardList(@RequestParam(defaultValue = "1") int pageNo, Model model) {
		// 1. 요청 데이터 읽기 ( 전달인자로 대체 )
		// 2. 데이터 처리 ( 데이터 조회 )		
		List<BoardDto> boards = boardService.findBoardByPage(pageNo, PAGE_SIZE);
		int boardCount = boardService.findBoardCount();
		
		ThePager pager = new ThePager(boardCount, pageNo, PAGE_SIZE, PAGER_SIZE, LINK_URL);
		
		// 3. View에서 읽을 수 있도록 데이터 저장
		model.addAttribute("boards", boards);
		model.addAttribute("pager", pager);
		model.addAttribute("pageNo", pageNo);
		
		// 4. View or Controller로 이동
		return "board/list"; 	// /WEB-INF/views/ + board/list + .jsp
	}
	
	@GetMapping(path = { "/detail.action" })
	public String showBoardDetail(@RequestParam(defaultValue = "-1") int boardNo, 
								  @RequestParam(defaultValue = "-1") int pageNo,
								  HttpSession session, 
								  Model model) {		
		// 1. 요청 데이터 읽기 ( 전달인자로 대체 )	
		if (boardNo == -1 || pageNo == -1) { // 요청 데이터가 잘못된 경우
			return "redirect:list.action";
		}
		
		// 2. 데이터 처리
		ArrayList<Integer> readList = (ArrayList<Integer>)session.getAttribute("read-list");
		if (readList == null) { // 세션에 목록이 없으면 
			readList = new ArrayList<>(); // 목록 새로 만들기
			session.setAttribute("read-list", readList); // 세션에 목록 등록
		}
		
		if (!readList.contains(boardNo)) { // 현재 글 번호가 읽은 글 목록에 포함되지 않은 경우
			boardService.increaseBoardReadCount(boardNo); // 글 조회수 증가
			readList.add(boardNo); // 읽은 글 목록에 현개 글 번호 추가			
		}
		
		BoardDto board = boardService.findBoardByBoardNo(boardNo);
		
		if (board == null) { // 조회되지 않은 경우 (글 번호가 잘못되었거나 또는 삭제된 글인 경우)
			return "redirect:list.action";
		}
		
		// 3. View에서 읽을 수 있도록 데이터 전달
		model.addAttribute("board", board);
		model.addAttribute("pageNo", pageNo);		
		
		// 4. View 또는 Controller로 이동
		return "board/detail";
	}
	
	@GetMapping(path = { "/write.action" })
	public String showWriteBoardForm() {
		
		return "board/write";
	}

//	@PostMapping(path = { "/write.action" })
//	public String writeBoard(BoardDto board, 
//							 //@RequestParam("attach") MultipartFile[] attaches) {
//							 @RequestParam("attach") MultipartFile attach) {
	
	@PostMapping(path = { "/write.action" })
	public String writeBoard(BoardDto board, MultipartHttpServletRequest req) {
		// 1. 요청 데이터 읽기 (전달인자로 대체)
		MultipartFile attach = req.getFile("attach");

		if (attach != null) { //내용이 있는 경우
			// 2. 데이터 처리
			ServletContext application = req.getServletContext();
			String path = application.getRealPath("/board-attachments");
			String fileName = attach.getOriginalFilename(); //파일 이름 가져오기
			if (fileName != null && fileName.length() > 0) {
				String uniqueFileName = Util.makeUniqueFileName(fileName);
				
				try {				
					attach.transferTo(new File(path, uniqueFileName));//파일 저장
					
					// 첨부파일 정보를 객체에 저장
					ArrayList<BoardAttachDto> attachments = new ArrayList<>(); // 첨부파일 정보를 저장하는 DTO 객체
					BoardAttachDto attachment = new BoardAttachDto();
					attachment.setUserFileName(fileName);
					attachment.setSavedFileName(uniqueFileName);
					attachments.add(attachment);
					board.setAttachments(attachments);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		boardService.writeBoard(board);
		// 3. View에서 읽을 수 있도록 데이터 저장
		// 4. View 또는 Controller로 이동
		return "redirect:list.action";
	}
	
//	@GetMapping(path = { "/delete.action" })
//	public String deleteBoard(@RequestParam(defaultValue = "-1")int boardNo, 
//							  @RequestParam(defaultValue = "-1")int pageNo,
//							  Model model) {
//		if (boardNo == -1 || pageNo == -1) {
//			//return "redirect:list.action";
//			model.addAttribute("error_type", "delete");
//			model.addAttribute("message", "잘못된 요청 : 글 번호 또는 페이지 번호가 없습니다.");
//			return "board/error"; //	/WEB-INF/views/ + board/error + .jsp
//		}
//		
//		boardService.deleteBoard(boardNo);
//		
//		return "redirect:list.action?pageNo=" + pageNo;
//	}
	@GetMapping(path = { "/{boardNo}/delete.action" })
	public String deleteBoard(@PathVariable("boardNo") int boardNo, 
							  @RequestParam(defaultValue = "-1")int pageNo,
							  Model model) {	
		
		if (pageNo == -1) {
			//return "redirect:list.action";
			model.addAttribute("error_type", "delete");
			model.addAttribute("message", "잘못된 요청 : 글 번호 또는 페이지 번호가 없습니다.");
			return "board/error"; //	/WEB-INF/views/ + board/error + .jsp
		}
		
		boardService.deleteBoard(boardNo);
		
		return "redirect:/board/list.action?pageNo=" + pageNo;
	}
	
	@GetMapping(path = { "/download.action" })
	public View download(@RequestParam(defaultValue = "-1") int attachNo, Model model) {
		
		if (attachNo == -1) {
			model.addAttribute("error_type", "download");
			model.addAttribute("message", "첨부파일 번호가 없습니다.");
		}
		
		BoardAttachDto attachment = boardService.findBoardAttachByAttachNo(attachNo);
		
		// View에게 전달할 데이터 저장
		model.addAttribute("attachment", attachment);
		
		DownloadView view = new DownloadView();
		
		return view;
	}
	
	@GetMapping(path = { "/edit.action" })
	public String showBoardEditForm(@RequestParam(defaultValue = "-1") int boardNo, 
									@RequestParam(defaultValue = "-1") int pageNo,
									Model model) {
		
		if (boardNo == -1 || pageNo == -1) {
			model.addAttribute("error_type", "edit");
			model.addAttribute("message", "글 번호 또는 페이지 번호가 없습니다.");
			return "board/error";
		}
		
		BoardDto board = boardService.findBoardByBoardNo(boardNo);
		
		model.addAttribute("board", board);
		model.addAttribute("boardNo", boardNo);
		model.addAttribute("pageNo", pageNo);
		
		return "board/edit"; //   /WEB-INF/views/ + board/edit + .jsp
				
	}
	
	@PostMapping(path = { "/edit.action" })
	public String modifyBoard(@RequestParam(defaultValue = "-1") int pageNo,
							  BoardDto board,
							  Model model) {
		
		if (board.getBoardNo() == 0 || pageNo == -1) {
			model.addAttribute("error_type", "edit");
			model.addAttribute("message", "글 번호 또는 페이지 번호가 없습니다.");
			return "board/error";
		}
		
		boardService.modifyBoard(board);
		
		return "redirect:detail.action?boardNo=" + board.getBoardNo() + "&pageNo=" + pageNo;
		
	}

}















