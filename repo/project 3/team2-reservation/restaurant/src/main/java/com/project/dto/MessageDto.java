package com.project.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MessageDto {
	private int msgId;
	private String msgName;
	private String msgEmail;
	private String msgTitle;
	private String msgDetail;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh-mm-ss")
	private Date msgDate;
	private boolean msgDeleted;
}
