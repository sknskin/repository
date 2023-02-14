package com.springexample.springioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain {

	public static void main(String[] args) {
		
//		//1. 클래스를 직접 사용해서 객체 생성 
//		MyServiceConsumer consumer = new MyServiceConsumer();
//		consumer.doSomething();
		
		//2. IoC 컨테이너 사용해서 객체 생성
		AnnotationConfigApplicationContext container = 
				new AnnotationConfigApplicationContext(MyBeanConfig.class);
		
		ServiceConsumer consumer = container.getBean("serviceConsumer", ServiceConsumer.class);
		consumer.doSomething();
		
		ServiceConsumer consumer2 = container.getBean("serviceConsumer2", ServiceConsumer.class);
		consumer2.doSomething();
	}

}


















