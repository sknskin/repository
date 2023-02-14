package com.demoweb.dao;

import java.util.List;

import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardDto;

public interface BoardDao {

	void insertBoard(BoardDto board);

	List<BoardDto> selectAllBoard();

	List<BoardDto> selectBoardByPage(int from, int count);

	int selectBoardCount();

	BoardDto selectBoardByBoardNo(int boardNo);

	void updateBoardReadCount(int boardNo);

	void deleteBoard(int boardNo);

	void insertBoardAttach(BoardAttachDto attachment);

	List<BoardAttachDto> selectBoardAttachByBoardNo(int boardNo);

	BoardAttachDto selectBoardAttachByAttachNo(int attachNo);

	void updateBoard(BoardDto board);

}