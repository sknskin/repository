package com.project.config;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.interceptor.AuthInterceptor;

@Configuration
public class ProjectMvcConfig implements WebMvcConfigurer { // web mvc 설정 클래스
	
	@Autowired
	private ServletContext context;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new AuthInterceptor())
				.addPathPatterns("/admin/**");
		
	}
	
	// custom image path
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		String path = System.getProperty("user.home"); // 사용자 home directory
		path = path + File.separator + "product-images" + File.separator;
		
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
		registry.addResourceHandler("/product-images/**")
				// .addResourceLocations("classpath:/resources/product-images/");
				.addResourceLocations("file:///" + path);
		
	}
	
}
