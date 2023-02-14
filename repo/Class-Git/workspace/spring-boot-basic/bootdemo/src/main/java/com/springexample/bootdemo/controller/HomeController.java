package com.springexample.bootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + 모든 메서드에 @ResponseBody 설정
public class HomeController {
	
	@GetMapping(path = { "/", "/home" })
	public String home() {
		
		return "This is home";
		
	}
	
	@GetMapping(path = { "/hello" })
	public String hello(String name) {
		
		String greetings = "Hello, ";
		if (name == null) {
			greetings += "Everbody";
		} else {
			greetings += name;
		}
		
		return greetings;
		
	}

}
