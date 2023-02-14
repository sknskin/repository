package com.demoweb.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;

import com.demoweb.common.Util;
import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardCommentDto;
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
	private final String LINK_URL = "list"; // 페이지 번호를 클릭했을 때 이동할 페이지 경로
	
	@Autowired
	@Qualifier("boardService")
	private BoardService boardService;
	
	@GetMapping(path = { "/list" })
	public String showBoardList(@RequestParam(defaultValue = "1") int pageNo, Model model) {
		
		// 1. without paging
//		List<BoardDto> boards = boardService.findAllBoard();
//		model.addAttribute("boards", boards);
//		model.addAttribute("pageNo", pageNo);
		
		// 2. with paging 1
//		List<BoardDto> boards = boardService.findBoardByPage(pageNo, PAGE_SIZE);
//		long boardCount = boardService.findBoardCount();
//		
//		ThePager pager = new ThePager((int)boardCount, pageNo, PAGE_SIZE, PAGER_SIZE, LINK_URL);
//		
//		model.addAttribute("boards", boards);
//		model.addAttribute("pager", pager);
//		model.addAttribute("pageNo", pageNo);
		
		// 3. with paging 2
		HashMap<String, Object> pagingData = boardService.findBoardByPage2(pageNo - 1, PAGE_SIZE);
	
		ThePager pager = new ThePager((int)pagingData.get("dataCount"), pageNo, PAGE_SIZE, PAGER_SIZE, LINK_URL);
		
		model.addAttribute("boards", pagingData.get("boards"));
		model.addAttribute("pager", pager);
		model.addAttribute("pageNo", pageNo);
		
		return "board/list"; 	// /WEB-INF/views/ + board/list + .jsp
	}
	
	@GetMapping(path = { "/detail" })
	public String showBoardDetail(@RequestParam(defaultValue = "-1") int boardNo, 
								  @RequestParam(defaultValue = "-1") int pageNo,
								  HttpSession session, 
								  Model model) {		
		// 1. 요청 데이터 읽기 ( 전달인자로 대체 )	
		if (boardNo == -1 || pageNo == -1) { // 요청 데이터가 잘못된 경우
			return "redirect:list";
		}
		
		// 2. 데이터 처리
//		ArrayList<Integer> readList = (ArrayList<Integer>)session.getAttribute("read-list");
//		if (readList == null) { // 세션에 목록이 없으면 
//			readList = new ArrayList<>(); // 목록 새로 만들기
//			session.setAttribute("read-list", readList); // 세션에 목록 등록
//		}
//		
//		if (!readList.contains(boardNo)) { // 현재 글 번호가 읽은 글 목록에 포함되지 않은 경우
//			boardService.increaseBoardReadCount(boardNo); // 글 조회수 증가
//			readList.add(boardNo); // 읽은 글 목록에 현개 글 번호 추가			
//		}
		boardService.increaseBoardReadCount(boardNo); // 글 조회수 증가
		
		BoardDto board = boardService.findBoardByBoardNo(boardNo);
		
		if (board == null) { // 조회되지 않은 경우 (글 번호가 잘못되었거나 또는 삭제된 글인 경우)
			return "redirect:list";
		}
		
		// 3. View에서 읽을 수 있도록 데이터 전달
		model.addAttribute("board", board);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("enter", "\n");
		
		// 4. View 또는 Controller로 이동
		return "board/detail";
	}
	
	@GetMapping(path = { "/write" })
	public String showWriteBoardForm() {
		
		return "board/write";
	}

//	@PostMapping(path = { "/write" })
//	public String writeBoard(BoardDto board, 
//							 //@RequestParam("attach") MultipartFile[] attaches) {
//							 @RequestParam("attach") MultipartFile attach) {
	
	@PostMapping(path = { "/write" })
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
		return "redirect:list";
	}
	
//	@GetMapping(path = { "/delete" })
//	public String deleteBoard(@RequestParam(defaultValue = "-1")int boardNo, 
//							  @RequestParam(defaultValue = "-1")int pageNo,
//							  Model model) {
//		if (boardNo == -1 || pageNo == -1) {
//			//return "redirect:list";
//			model.addAttribute("error_type", "delete");
//			model.addAttribute("message", "잘못된 요청 : 글 번호 또는 페이지 번호가 없습니다.");
//			return "board/error"; //	/WEB-INF/views/ + board/error + .jsp
//		}
//		
//		boardService.deleteBoard(boardNo);
//		
//		return "redirect:list?pageNo=" + pageNo;
//	}
	@GetMapping(path = { "/{boardNo}/delete" })
	public String deleteBoard(@PathVariable("boardNo") int boardNo, 
							  @RequestParam(defaultValue = "-1")int pageNo,
							  Model model) {	
		
		if (pageNo == -1) {
			//return "redirect:list";
			model.addAttribute("error_type", "delete");
			model.addAttribute("message", "잘못된 요청 : 글 번호 또는 페이지 번호가 없습니다.");
			return "board/error"; //	/WEB-INF/views/ + board/error + .jsp
		}
		
		boardService.deleteBoard(boardNo);
		
		return "redirect:/board/list?pageNo=" + pageNo;
	}
	
	@GetMapping(path = { "/download" })
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
	
	@GetMapping(path = { "/edit" })
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
	
	@PostMapping(path = { "/edit" })
	public String modifyBoard(@RequestParam(defaultValue = "-1") int pageNo,
							  BoardDto board,
							  Model model) {
		
		if (board.getBoardNo() == 0 || pageNo == -1) {
			model.addAttribute("error_type", "edit");
			model.addAttribute("message", "글 번호 또는 페이지 번호가 없습니다.");
			return "board/error";
		}
		
		boardService.modifyBoard(board);
		
		return "redirect:detail?boardNo=" + board.getBoardNo() + "&pageNo=" + pageNo;
		
	}
	
	/////////////////////////////////////////////////////////////////////////////
	
	@GetMapping(path = { "/comment-list" })
	public String showCommentList(int boardNo, Model model) {
		
		List<BoardCommentDto> comments = boardService.findBoardCommentByBoardNo(boardNo);
		
		// View에서 읽을 수 있도록 데이터 저장
		model.addAttribute("comments", comments);
		
		return "board/comment-list"; //  /WEB-INF/views/ + board/comment-list + .jsp
	}
	
	@PostMapping(path = { "/write-comment" })
	@ResponseBody
	public String writeComment(BoardCommentDto commentDto, int pageNo) {
		// 1. 요청 데이터 읽기 ( 전달인자로 대체 )
		// 2. 요청 처리
		boardService.writeComment(commentDto); // commentDto에 자동 증가된 commentNo가 저장됩니다.
		// 최상위 댓글의 글번호를 그룹번호로 저장
		boardService.updateGroupNo(commentDto.getCommentNo(), commentDto.getCommentNo());		
		// 3. View에서 읽을 수 있도록 데이터 저장		
		// 4. View 또는 다른 컨터롤러로 이동
		// return String.format("redirect:detail?boardNo=%d&pageNo=%d", commentDto.getBoardNo(), pageNo);
		return "success";
	}
	
//	@GetMapping(path = { "/delete-comment" })
//	public String deleteComment(@RequestParam(defaultValue = "-1") int commentNo, 
//								@RequestParam(defaultValue = "-1") int boardNo, 
//								@RequestParam(defaultValue = "-1") int pageNo) {
//		//1. 요청 데이터 읽기 (전달인자로 대체)
//		if (commentNo == -1 || boardNo == -1 || pageNo == -1) {
//			return "redirect:list";
//		}
//		// 2. 데이터 처리
//		boardService.deleteComment(commentNo);
//		return String.format("redirect:detail?boardNo=%d&pageNo=%d", boardNo, pageNo);
//	}
	@GetMapping(path = { "/delete-comment" })
	@ResponseBody // 반환 값은 view 이름이 아니고 응답 컨텐츠 입니다.
	public String deleteComment(@RequestParam(defaultValue = "-1") int commentNo) {
		
		//1. 요청 데이터 읽기 (전달인자로 대체)
		if (commentNo == -1) {
			return "fail";	// "fail" 문자열을 응답 (@ResponseBody 때문에)
		}
		
		// 2. 데이터 처리
		boardService.deleteComment(commentNo);		
		
		return "success"; // "success" 문자열을 응답 (@ResponseBody 때문에)
	}
	
	@PostMapping(path = { "/update-comment" })
	@ResponseBody
	public String updateComment(BoardCommentDto comment) {
		
		boardService.updateComment(comment);
		
		return "success";
	}
	
	@PostMapping(path = { "/write-recomment" })
	@ResponseBody
	public String writeReComment(BoardCommentDto commentDto) {
		// 1. 요청 데이터 읽기 ( 전달인자로 대체 )
		// 2. 요청 처리
		boardService.writeReComment(commentDto); // commentDto에 자동 증가된 commentNo가 저장됩니다.

		// 3. View에서 읽을 수 있도록 데이터 저장		
		// 4. View 또는 다른 컨터롤러로 이동
		// return String.format("redirect:detail?boardNo=%d&pageNo=%d", commentDto.getBoardNo(), pageNo);
		return "success";
	}

}















