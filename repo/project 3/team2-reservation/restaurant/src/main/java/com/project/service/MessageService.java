package com.project.service;

import java.math.BigDecimal;
import java.util.List;

import com.project.dto.MessageDto;
import com.project.entity.MessageEntity;

public interface MessageService {

	void sendMessage(MessageDto message);
	
	public default MessageDto messageEntityToDto(MessageEntity messageEntity) {
		MessageDto messageDto = new MessageDto();
		messageDto.setMsgId(messageEntity.getMsgId());
		messageDto.setMsgName(messageEntity.getMsgName());
		messageDto.setMsgEmail(messageEntity.getMsgEmail());
		messageDto.setMsgTitle(messageEntity.getMsgTitle());
		messageDto.setMsgDetail(messageEntity.getMsgDetail());
		messageDto.setMsgDeleted(messageEntity.isMsgDeleted());
		
		return messageDto;
	}
	
	public default MessageEntity messageDtoToEntity(MessageDto messageDto) {
		MessageEntity messageEntity = MessageEntity.builder()
												   .msgId(messageDto.getMsgId())
												   .msgName(messageDto.getMsgName())
												   .msgEmail(messageDto.getMsgEmail())
												   .msgTitle(messageDto.getMsgTitle())
												   .msgDetail(messageDto.getMsgDetail())
												   .msgDeleted(messageDto.isMsgDeleted())
												   .build();
		
		return messageEntity;
	}

	List<MessageDto> findAllMessages();

	MessageEntity findByMsgId(int msgId);
	
	void confirmAndDeleteMessage(int msgId);
	
	List<MessageDto> findMessageByName(String msgName);

	// Administrator statistics message count
	List<BigDecimal[]> selectMessageCount();
	
}
