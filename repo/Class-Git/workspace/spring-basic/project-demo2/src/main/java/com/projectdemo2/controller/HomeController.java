package com.projectdemo2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping(path = { "/", "home" })
	public String home() {
		
		return "home"; // --> /WEB-INF/views/ + home + .jsp
	}
	
	@GetMapping(path = { "/icons" })
	public String icon() {
		
		return "icon";	// /WEB-INF/views/ + icon + .jsp
	}

}
