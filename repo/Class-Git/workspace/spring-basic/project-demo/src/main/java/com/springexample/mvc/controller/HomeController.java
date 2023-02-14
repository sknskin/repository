package com.springexample.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // @Component + Web configuration
public class HomeController {
	
	@RequestMapping(path = { "/" }) // FrontController에 연결 설정
	public String home() {
		
		return "table-demo"; // /WEB-INF/views/ + home + .jsp
	}

}







