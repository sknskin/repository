package com.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.dto.MessageDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_message")
public class MessageEntity {
	
	public MessageEntity(MessageDto message) {
		this.msgId = message.getMsgId();
		this.msgName = message.getMsgName();
		this.msgEmail = message.getMsgEmail();
		this.msgTitle = message.getMsgTitle();
		this.msgDetail = message.getMsgDetail();
		this.msgDeleted = message.isMsgDeleted();
	}
	
	public MessageDto exportMessageDto() {
		MessageDto message = new MessageDto();
		message.setMsgId(msgId);
		message.setMsgName(msgName);
		message.setMsgEmail(msgEmail);
		message.setMsgTitle(msgTitle);
		message.setMsgDetail(msgDetail);
		message.setMsgDeleted(msgDeleted);
		
		return message;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int msgId;
	
	@Column
	private String msgName;
	
	@Column
	private String msgEmail;
	
	@Column
	private String msgTitle;
	
	@Column
	private String msgDetail;
	
	@Column
	@Builder.Default
	private Date msgDate = new Date();
	
	@Column
	@Builder.Default
	private boolean msgDeleted = false;
}
