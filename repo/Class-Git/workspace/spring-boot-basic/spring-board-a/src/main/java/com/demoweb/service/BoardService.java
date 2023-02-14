package com.demoweb.service;

import java.util.List;

import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardCommentDto;
import com.demoweb.dto.BoardDto;

public interface BoardService {

	void writeBoard(BoardDto board);

	List<BoardDto> findAllBoard();

	List<BoardDto> findBoardByPage(int pageNo, int pageSize);

	BoardDto findBoardByBoardNo(int boardNo);

	void increaseBoardReadCount(int boardNo);

	void deleteBoard(int boardNo);

	BoardAttachDto findBoardAttachByAttachNo(int attachNo);

	int findBoardCount();

	void modifyBoard(BoardDto board);
	
	////////////////////////////////////////////
	
	void writeComment(BoardCommentDto comment);
	
	void updateGroupNo(int commentNo, int groupNo);

	void deleteComment(int commentNo);

	List<BoardCommentDto> findBoardCommentByBoardNo(int boardNo);

	void updateComment(BoardCommentDto comment);

	void writeReComment(BoardCommentDto commentDto);

}