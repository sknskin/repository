package com.demoweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demoweb.interceptor.AuthInterceptor;

@Configuration
public class DemoWebMvcConfig implements WebMvcConfigurer { // web mvc 설정 클래스

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new AuthInterceptor())
				.addPathPatterns("/board/**");
		
	}
	
}
