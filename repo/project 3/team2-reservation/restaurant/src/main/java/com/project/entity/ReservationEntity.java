package com.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_reservation")
public class ReservationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int resId;
	
	@Column(length = 20, nullable = false)
	private String resName;
	
	@Column(length = 20, nullable = false)
	private String resEmail;
	
	@Column
	private Date resDate;

	@Column
	private int resTime;
	
	@Column
	private int resAdult;
	
	@Column
	private int resChild;
	
	@Column(nullable = false)
	private int resTableId;
	
	
	
}







