package com.springexample.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springexample.mvc.dto.Person;

// @Controller
public class DemoController2 {
	
//	// @RequestMapping(path = { "/demo/param" }, method=RequestMethod.GET)
//	@GetMapping(path = { "/demo/param" })
//	public String processParam(HttpServletRequest req) { // 전달인자를 쓰면 스프링 프레임워크가 알아서 전달인자에 값 전달
//		// 요청 데이터 읽기
//		String data1 = req.getParameter("data1");
//		String sData2 = req.getParameter("data2");
//		int data2 = Integer.parseInt(sData2);
//		
//		System.out.printf("[%s][%d]\n", data1, data2);		
//		
//		return "result";	 // /WEB-INF/view/ + result + .jsp
//	}
	
//	// Get 요청 방식 처리 + 이름이 같은 전달인자로 데이터 읽기
//	@GetMapping(path = { "/demo/param" })
//	public String processParam(String data1, int data2) { // 전달인자를 쓰면 스프링 프레임워크가 알아서 전달인자에 값 전달
//		// 요청 데이터 읽기
//		
//		System.out.printf("[%s][%d]\n", data1, data2);		
//		
//		return "result";	 // /WEB-INF/view/ + result + .jsp
//	}
	
	// Get 요청 방식 처리 + 이름이 다른 전달인자로 데이터 읽기
	@GetMapping(path = { "/demo/param" })
	public String processParam(@RequestParam(name="data1")String data3, 
							   @RequestParam(name="data2")int data4) { // 전달인자를 쓰면 스프링 프레임워크가 알아서 전달인자에 값 전달
		// 요청 데이터 읽기
		
		System.out.printf("[%s][%d]\n", data3, data4);		
		
		return "result";	 // /WEB-INF/view/ + result + .jsp
	}
	
	/////////////////////////////////////
	//@RequestMapping(path = { "/demo/param" }, method = RequestMethod.POST)
	@PostMapping(path = { "/demo/param" })
	public String processParam2(Person person) { // DTO를 전달인자로 쓰면 각 필드에 데이터 자동으로 저장
		
		System.out.println(person);
		
		return "result";
	}

}












