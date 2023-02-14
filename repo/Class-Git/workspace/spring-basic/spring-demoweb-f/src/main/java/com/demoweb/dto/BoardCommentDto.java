package com.demoweb.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardCommentDto {
	
	private int commentNo;
	private int boardNo;
	private String writer;
	private String content;
	private Date regDate;
	private boolean deleted;
	
	// 아래 세 개의 변수는 글의 논리적인 순서를 관리
	private int groupNo;	// 그룹 번호
	private int step;		// 그룹 내 순서 번호
	private int depth;		// 들여쓰기 정도

}
