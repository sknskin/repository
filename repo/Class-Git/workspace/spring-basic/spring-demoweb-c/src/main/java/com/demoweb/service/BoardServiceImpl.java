package com.demoweb.service;

import java.util.List;

import com.demoweb.dao.BoardDao;
import com.demoweb.dao.BoardDaoImpl;
import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardDto;
import com.demoweb.mapper.BoardMapper;

import lombok.Setter;

public class BoardServiceImpl implements BoardService {
	
	@Setter
	private BoardDao boardDao;
	
	@Setter private BoardMapper boardMapper;
	
	// 사용자가 입력한 게시글 데이터를 받아서 글쓰기 처리
	@Override
	public void writeBoard(BoardDto board) {
		
		// boardDao.insertBoard(board); // insert 하면서 boardNo 자동 생성 ( 글 번호 가져오기 필요 )
		boardMapper.insertBoard(board); // insert 하면서 boardNo 자동 생성 ( 글 번호 가져오기 필요 )
		if (board.getAttachments() != null) {
			for (BoardAttachDto attachment : board.getAttachments()) {
				attachment.setBoardNo(board.getBoardNo()); // 새로 만들어진 글번호를 Attach 객체에 저장
				boardDao.insertBoardAttach(attachment);
			}
		}
		
	}
	
	// 모든 게시글 조회해서 반환
	@Override
	public List<BoardDto> findAllBoard() {
		
		// BoardDao boardDao = new BoardDao();
		List<BoardDto> boards = boardDao.selectAllBoard();
		return boards;
		
	}
	
	@Override
	public List<BoardDto> findBoardByPage(int pageNo, int pageSize) {
		
		int from = (pageNo - 1) * pageSize;
		int count = pageSize;
		
		//List<BoardDto> boards = boardDao.selectBoardByPage(from, count);
		List<BoardDto> boards = boardMapper.selectBoardByPage(from, count);
		return boards;
		
	}
	
	// 글 번호를 받아서 게시글 조회 및 반환
	@Override
	public BoardDto findBoardByBoardNo(int boardNo) {
		
		// BoardDto board = boardDao.selectBoardByBoardNo(boardNo);
		BoardDto board = boardMapper.selectBoardByBoardNo(boardNo);
		// MyBatis에서 관계매핑을 사용해서 Board와 BoardAttach를 한 번에 조회하는 경우 아래 구현은 불필요
//		if (board != null) {
//			// List<BoardAttachDto> attachments = boardDao.selectBoardAttachByBoardNo(boardNo);
//			List<BoardAttachDto> attachments = boardMapper.selectBoardAttachByBoardNo(boardNo);		
//			board.setAttachments(attachments);
//		}
		
		return board;
		
	}
	
	// 글 번호를 받아서 게시글 조회수 증가
	@Override
	public void increaseBoardReadCount(int boardNo) {
		
		boardDao.updateBoardReadCount(boardNo);
		
	}
	
	// 글 번호를 받아서 게시글 삭제
	@Override
	public void deleteBoard(int boardNo) {
		
		boardDao.deleteBoard(boardNo);
		
	}
	
	// 첨부파일 번호를 받아서 첨부파일 데이터 조회 및 반환
	@Override
	public BoardAttachDto findBoardAttachByAttachNo(int attachNo) {
		
		BoardAttachDto attachment = boardDao.selectBoardAttachByAttachNo(attachNo);
		return attachment;
		
	}

	@Override
	public int findBoardCount() {
		
		// int boardCount = boardDao.selectBoardCount();
		int boardCount = boardMapper.selectBoardCount();
		return boardCount;
		
	}
	
	@Override
	public void modifyBoard(BoardDto board) {
		
		boardDao.updateBoard(board);
		
	}


}










