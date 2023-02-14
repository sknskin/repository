package com.springexample.springioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("serviceConsumer")
public class MyServiceConsumer implements ServiceConsumer {

	@Autowired @Qualifier("messageService") // <property name="messageService" ref="messageService"와 유사
	private MessageService messageService; // = new MyMessageService();
	
//	@Autowired @Qualifier("messageService")
//	public void setMessageService(MessageService messageService) {
//		this.messageService = messageService;
//	}
	//////////////////////////////////////////
	
	private TimeService timeService; // = new MyTimeService();
	
	@Autowired @Qualifier("timeService")
	public void setTimeService(TimeService timeService) {
		this.timeService = timeService;
	}

	public void doSomething() {
		String message = messageService.getMessage();
		System.out.println(message);
		message = timeService.getTimeString();
		System.out.println(message);
	}

}
