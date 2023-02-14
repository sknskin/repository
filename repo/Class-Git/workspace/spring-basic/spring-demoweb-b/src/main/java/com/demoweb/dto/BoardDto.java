package com.demoweb.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardDto {
		
	private int boardNo;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int readCount;
	private boolean deleted;
	
	// Board 테이블과 BoartAttach 테이블 사이의 1:Many 관계를 구현한 필드 (변수)
	private List<BoardAttachDto> attachments;
	// Board 테이블과 BoartAttach 테이블 사이의 1:1 관계를 구현한 필드 (변수)
	// private BoardAttachDto attachment;
	
	
}
