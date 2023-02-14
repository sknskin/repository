package com.demoweb.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotationLogger {
	
	@Pointcut("execution(* *..*.Board*.*(..))")
	public void boardPointcut() {}
	
	public void logBefore(JoinPoint joinPoint) {
		System.out.printf("logBefore ===> %s.%s\n",
				joinPoint.getSignature().getDeclaringType(),
				joinPoint.getSignature().getName());
	}
	
	public void logAfter(JoinPoint joinPoint) {
		System.out.println("logAfter");
	}
	
	// @Around("within(*..*.Board*)")
	@Around("boardPointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) {
		
		long begin = System.currentTimeMillis(); // 현재 시간을 1/1000 단위 시간 값
		
		Object returnValue = null;
		try {
			returnValue = joinPoint.proceed();	// 실제 호출될 메서드 실행
		} catch (Throwable e) {			
			e.printStackTrace();
		} 
		
		long end = System.currentTimeMillis();
		System.out.printf("===>%s.%s [ 실행 소요 시간 : %d]\n",
				joinPoint.getSignature().getDeclaringType(),
				joinPoint.getSignature().getName(),
				(end - begin));
		
		return returnValue;
	}

}
