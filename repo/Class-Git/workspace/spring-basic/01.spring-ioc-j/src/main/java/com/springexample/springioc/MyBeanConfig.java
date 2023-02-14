package com.springexample.springioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // app-context.xml과 같은 spring bean 설정 파일의 코드 버전
public class MyBeanConfig {
	
	@Bean // <bean id=""와 같은 역할
	public MessageService messageService() { // 메서드의 이름이 <bean id에 해당 
		return new MyMessageService();
	}
	@Bean
	public TimeService timeService() {
		return new MyTimeService();
	}
	
	@Bean
	public ServiceConsumer serviceConsumer() {
		MyServiceConsumer serviceConsumer = new MyServiceConsumer();
 
		// 아래는 <property name="messageService" ref="messageService"와 같은 역할
		serviceConsumer.setMessageService(messageService());		
		serviceConsumer.setTimeService(timeService());
		
		return serviceConsumer;
	}
	@Bean
	public ServiceConsumer serviceConsumer2() {
		// 아래는 <constructor-arg ref="messageService"와 같은 역할
		MyServiceConsumer serviceConsumer = new MyServiceConsumer(messageService());
		
		serviceConsumer.setTimeService(timeService());
		
		return serviceConsumer;
	}

}














