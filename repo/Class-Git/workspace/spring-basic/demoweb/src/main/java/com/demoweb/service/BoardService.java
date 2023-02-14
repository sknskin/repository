package com.demoweb.service;

import java.util.List;

import com.demoweb.dao.BoardDao;
import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardDto;

public class BoardService {
	
	private BoardDao boardDao = new BoardDao();
	
	// 사용자가 입력한 게시글 데이터를 받아서 글쓰기 처리
	public void writeBoard(BoardDto board) {
		
		// BoardDao boardDao = new BoardDao();
		// board.getBoardNo() --> 0
		boardDao.insertBoard(board); // insert 하면서 boardNo 자동 생성 ( 글 번호 가져오기 필요 )
		// board.getBoardNo() --> 새로 만들어진 글번호
		
		for (BoardAttachDto attachment : board.getAttachments()) {
			attachment.setBoardNo(board.getBoardNo()); // 새로 만들어진 글번호를 Attach 객체에 저장
			boardDao.insertBoardAttach(attachment);
		}
		
	}
	
	// 모든 게시글 조회해서 반환
	public List<BoardDto> findAllBoard() {
		
		// BoardDao boardDao = new BoardDao();
		List<BoardDto> boards = boardDao.selectAllBoard();
		return boards;
		
	}
	
	public List<BoardDto> findBoardByPage(int pageNo, int pageSize) {
		
		int from = (pageNo - 1) * pageSize;
		int count = pageSize;
		
		List<BoardDto> boards = boardDao.selectBoardByPage(from, count);
		return boards;
		
	}
	
	// 글 번호를 받아서 게시글 조회 및 반환
	public BoardDto findBoardByBoardNo(int boardNo) {
		
		BoardDto board = boardDao.selectBoardByBoardNo(boardNo);
		List<BoardAttachDto> attachments = boardDao.selectBoardAttachByBoardNo(boardNo);
		board.setAttachments(attachments);
		
		return board;
		
	}
	
	// 글 번호를 받아서 게시글 조회수 증가
	public void increaseBoardReadCount(int boardNo) {
		
		boardDao.updateBoardReadCount(boardNo);
		
	}
	
	// 글 번호를 받아서 게시글 삭제
	public void deleteBoard(int boardNo) {
		
		boardDao.deleteBoard(boardNo);
		
	}
	
	// 첨부파일 번호를 받아서 첨부파일 데이터 조회 및 반환
	public BoardAttachDto findBoardAttachByAttachNo(int attachNo) {
		
		BoardAttachDto attachment = boardDao.selectBoardAttachByAttachNo(attachNo);
		return attachment;
		
	}

	public int findBoardCount() {
		
		int boardCount = boardDao.selectBoardCount();
		return boardCount;
		
	}
	
	public void modifyBoard(BoardDto board) {
		
		boardDao.updateBoard(board);
		
	}


}










