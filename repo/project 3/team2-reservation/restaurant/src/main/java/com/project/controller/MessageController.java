package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dto.MessageDto;
import com.project.entity.MessageEntity;
import com.project.service.MessageService;

@Controller
public class MessageController {

	@Autowired
	@Qualifier("messageService")
	private MessageService messageService;
	
	// Contact page (send message to Administrator(and DB))
	@PostMapping(path= {"send-message"})
	public String sendMsg(MessageDto message) {
		
		messageService.sendMessage(message);
		
		return "redirect:/contact";
	}
	
	// Administrator page (Administrator message with contact)
	@GetMapping(path = { "/admin/adminMessage" })
	public String adminMessage(Model model) {

		List<MessageDto> messages = messageService.findAllMessages();

		model.addAttribute("messages", messages);

		return "admin/adminMessage";
	}
	
	// Message Detail page
	@GetMapping(path = { "/admin/adminMessageDetail" })
	public String adminMessageDetail(@RequestParam(defaultValue = "-1") int msgId, Model model) {
		
		MessageEntity message = messageService.findByMsgId(msgId);
		
		model.addAttribute("message", message);
		
		return "admin/adminMessageDetail";
	}
	
	// Message Detail page (confirm funciton)
	@GetMapping(path = { "/confirm" })
	public String adminMessageConfirm(@RequestParam(defaultValue = "-1") int msgId) {
		
		messageService.confirmAndDeleteMessage(msgId);
		
		return "redirect:/admin/adminMessage";
	}
	
	// Message User Search page
	@PostMapping(path = { "search-message-by-name" })
	public String userMessageSearchByName(String msgName, Model model) {
		
		List<MessageDto> list = messageService.findMessageByName(msgName);

		model.addAttribute("list", list);

		return "message/message";
	}
	
	
}
