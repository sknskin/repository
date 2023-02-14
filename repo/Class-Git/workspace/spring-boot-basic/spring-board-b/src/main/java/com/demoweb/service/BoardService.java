package com.demoweb.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;

import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardCommentDto;
import com.demoweb.dto.BoardDto;
import com.demoweb.entity.BoardAttachEntity;
import com.demoweb.entity.BoardEntity;

public interface BoardService {
	
	public default BoardDto boardEntityToDto(BoardEntity boardEntity) {
		BoardDto boardDto = new BoardDto();
		boardDto.setBoardNo(boardEntity.getBoardNo());
		boardDto.setWriter(boardEntity.getWriter());
		boardDto.setTitle(boardEntity.getTitle());
		boardDto.setContent(boardEntity.getContent());
		boardDto.setReadCount(boardEntity.getReadCount());
		boardDto.setRegDate(boardEntity.getRegDate());
		boardDto.setDeleted(boardEntity.isDeleted());
		
		return boardDto;		
	}
	
	public default BoardEntity boardDtoToEntity(BoardDto boardDto) {
		BoardEntity boardEntity = BoardEntity.builder()
											 .boardNo(boardDto.getBoardNo())
											 .title(boardDto.getTitle())
											 .writer(boardDto.getWriter())
											 .content(boardDto.getContent())
											 .readCount(boardDto.getReadCount())
											 .regDate(boardDto.getRegDate())
											 .deleted(boardDto.isDeleted())
											 .build();		
		return boardEntity;
	}
	
	public default BoardAttachEntity boardAttachDtoToEntity(BoardAttachDto boardAttachDto) {
		BoardAttachEntity boardAttachEntity = BoardAttachEntity.builder()
															   .userFileName(boardAttachDto.getUserFileName())
															   .savedFileName(boardAttachDto.getSavedFileName())
															   .build();
		return boardAttachEntity;
	}
	
	public default BoardAttachDto boardAttachEntityToDto(BoardAttachEntity boardAttachEntity) {
		BoardAttachDto boardAttachDto = BoardAttachDto.builder()
													  .attachNo(boardAttachEntity.getAttachNo())
													  .userFileName(boardAttachEntity.getUserFileName())
													  .savedFileName(boardAttachEntity.getSavedFileName())
													  .downloadCount(boardAttachEntity.getDownloadCount())
													  .build();
		return boardAttachDto;
	}

	void writeBoard(BoardDto board);

	List<BoardDto> findAllBoard();

	List<BoardDto> findBoardByPage(int pageNo, int pageSize);
	HashMap<String, Object> findBoardByPage2(int pageNo, int pageSize);

	BoardDto findBoardByBoardNo(int boardNo);

	void increaseBoardReadCount(int boardNo);

	void deleteBoard(int boardNo);

	BoardAttachDto findBoardAttachByAttachNo(int attachNo);

	long findBoardCount();

	void modifyBoard(BoardDto board);
	
	////////////////////////////////////////////
	
	void writeComment(BoardCommentDto comment);
	
	void updateGroupNo(int commentNo, int groupNo);

	void deleteComment(int commentNo);

	List<BoardCommentDto> findBoardCommentByBoardNo(int boardNo);

	void updateComment(BoardCommentDto comment);

	void writeReComment(BoardCommentDto commentDto);

}