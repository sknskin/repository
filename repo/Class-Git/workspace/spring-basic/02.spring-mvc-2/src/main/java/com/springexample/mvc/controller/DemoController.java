package com.springexample.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.springexample.mvc.dto.Person;

@Controller
@RequestMapping(path = { "/demo" })
public class DemoController {
	
//	// @RequestMapping(path = { "/param" }, method=RequestMethod.GET)
//	@GetMapping(path = { "/param" })
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
//	@GetMapping(path = { "/param" })
//	public String processParam(String data1, int data2) { // 전달인자를 쓰면 스프링 프레임워크가 알아서 전달인자에 값 전달
//		// 요청 데이터 읽기
//		
//		System.out.printf("[%s][%d]\n", data1, data2);		
//		
//		return "result";	 // /WEB-INF/view/ + result + .jsp
//	}
	
	// Get 요청 방식 처리 + 이름이 다른 전달인자로 데이터 읽기
	@GetMapping(path = { "/param" })
	public String processParam(@RequestParam(name="data1")String data3, 
							   @RequestParam(name="data2")int data4) { // 전달인자를 쓰면 스프링 프레임워크가 알아서 전달인자에 값 전달
		// 요청 데이터 읽기
		
		System.out.printf("[%s][%d]\n", data3, data4);		
		
		return "result";	 // /WEB-INF/view/ + result + .jsp
	}
	
	/////////////////////////////////////
	
//	// Post 요청 방식 처리 + DTO 전달인자로 데이터 읽기
//	//@RequestMapping(path = { "/demo/param" }, method = RequestMethod.POST)
//	@PostMapping(path = { "/param" })
//	public String processParam2(Person person) { // DTO를 전달인자로 쓰면 각 필드에 데이터 자동으로 저장
//		
//		System.out.println(person);
//		
//		return "result";
//	}
	
//	// 컨트롤러에서 View로 데이터 전달 : Request 객체를 사용
//	@PostMapping(path = { "/param" })
//	public String processParam2(Person person, HttpServletRequest req) { // DTO를 전달인자로 쓰면 각 필드에 데이터 자동으로 저장
//		
//		System.out.println(person);
//		
//		req.setAttribute("person2", person); // View (jsp)로 데이터 전달
//		
//		return "result";
//	}
	
	// 컨트롤러에서 View로 데이터 전달 : Model 타입 전달인자 사용
	@PostMapping(path = { "/param" })
	public String processParam2(Person person, // DTO 전달이자는 자동으로 View로 전달 
								Model model) { // Model 전달인자는 View로 데이터를 보내기 위한 도구
		
		System.out.println(person);
		
		// Model 타입 전달인자 객체에 데이터를 저장하면 Request객체에 데이터를 저장하고 View (jsp)로 데이터 전달
		model.addAttribute("person2", person); 
		
		return "result";
	}
	
//	@GetMapping(path = { "/redirect" })
//	public String redirect() {
//		
//		// return "redirect_result";  // /WEB-INF/views/ + redirect_result + .jsp
//		return "redirect:redirect_result"; // --> response.sendRedirect("redirect_result")
//	}
	@GetMapping(path = { "/redirect" })
	public View redirect() {
		
		// return "redirect_result";  // /WEB-INF/views/ + redirect_result + .jsp
		return new RedirectView("redirect_result");
	}
	@GetMapping(path = { "/redirect_result" })
	public String redirect_result() {
		
		return "redirect_result";  // /WEB-INF/views/ + redirect_result + .jsp
	}
	
	@GetMapping(path = { "/forward" })
	public String forward() {
		
		return "forward:/resources/forward_result.html";
	}

}












