package com.springexample.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // @Component + Web configuration
public class HomeController {
	
	@RequestMapping(path = { "/" }) // FrontController에 연결 설정
	public String home() {
		System.out.println("요청을 수신했습니다.");
		
		return "home"; // /WEB-INF/views/ + home + .jsp
	}

}







