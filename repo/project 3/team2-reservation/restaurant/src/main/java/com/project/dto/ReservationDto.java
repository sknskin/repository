package com.project.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReservationDto {

	private int resId;
	private int resAdult;
	private int resChild;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date resDate;
	private int resTime;
	private String resEmail;
	private String resName;
	private int resTableId;

}

