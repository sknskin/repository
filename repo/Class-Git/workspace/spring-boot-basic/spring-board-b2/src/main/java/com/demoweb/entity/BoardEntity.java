package com.demoweb.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.demoweb.dto.BoardDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_board")
public class BoardEntity {
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int boardNo;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String writer;
	
	@Column(length = 1000, nullable = false)
	private String content;
	
	@Builder.Default
	@Column
	private Date regDate = new Date();
	
	@Builder.Default
	@Column
	private int readCount = 0;
	
	@Builder.Default
	@Column
	private boolean deleted = false;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "boardNo")
	private Collection<BoardAttachEntity> attachments;
	
	

}

















