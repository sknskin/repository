package com.demoweb.entity;

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
@Table(name = "tbl_boardattach")
public class BoardAttachEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int attachNo;
	
//	@Column(nullable = false)
//	private int boardNo;
	
	@Column(nullable = false)
	private String userFileName;
	
	@Column(nullable = false)
	private String savedFileName;
	
	@Builder.Default
	@Column
	private int downloadCount = 0;
	
	
}
