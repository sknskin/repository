package com.springexample.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {
	
	@RequestMapping("/get-time")
	public String showTimeString() {
		
		return "showtime";	 // /WEB-INF/view/ + showtime + .jsp
	}

}
