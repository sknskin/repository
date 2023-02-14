package com.project.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.MessageDto;
import com.project.entity.MessageEntity;
import com.project.repository.MessageRepository;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	
	// send message in contact page
	@Override
	public void sendMessage(MessageDto message) {
		
		MessageEntity messageEntity = MessageEntity.builder().msgName(message.getMsgName())
												   			 .msgEmail(message.getMsgEmail())
												   			 .msgTitle(message.getMsgTitle())
												   			 .msgDetail(message.getMsgDetail())
												   			 .build();
		
		messageRepository.save(messageEntity);
	}

	// select message
	@Override
	public List<MessageDto> findAllMessages() {
		
		List<MessageEntity> messages = messageRepository.findAllMessage();
		
		ArrayList<MessageDto> list = new ArrayList<>();
		for (MessageEntity messageEntity : messages) {
			list.add(messageEntityToDto(messageEntity));
		}
		
		return list;
	}

	@Override
	public MessageEntity findByMsgId(int msgId) {
		
		MessageEntity message = messageRepository.findMessageByMsgId(msgId);
		
		return message;
	}
	
	@Override
	public void confirmAndDeleteMessage(int msgId) {
		
		messageRepository.deleteMessageInText(msgId);
	}
	
	@Override
	public List<MessageDto> findMessageByName(String msgName) {
		
		List<MessageEntity> entityList = messageRepository.findByName(msgName);

		ArrayList<MessageDto> list = new ArrayList<>();
		for (MessageEntity messageEntity : entityList) {
			list.add(messageEntityToDto(messageEntity));
		}
		
		return list;
	}
	

	// Administrator Inquiry statistics
	@Override
	public List<BigDecimal[]> selectMessageCount() {
		
		List<BigDecimal[]> message = messageRepository.findMessageStatistics();
		
		return message;
	}
	
}
