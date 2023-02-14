package com.demoweb.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class Logger {
	
	public void logBefore(JoinPoint joinPoint) {
		System.out.printf("logBefore ===> %s.%s\n",
				joinPoint.getSignature().getDeclaringType(),
				joinPoint.getSignature().getName());
	}
	
	public void logAfter(JoinPoint joinPoint) {
		System.out.println("logAfter");
	}
	
	public Object logAround(ProceedingJoinPoint joinPoint) {
		
		System.out.println("logAround 1");
		
		Object returnValue = null;
		try {
			returnValue = joinPoint.proceed();	// 실제 호출될 메서드 실행
		} catch (Throwable e) {			
			e.printStackTrace();
		} 
		
		System.out.println("logAround 2");
		
		return returnValue;
	}

}
