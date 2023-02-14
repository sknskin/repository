package com.demoweb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.demoweb.dto.MemberDto;
import com.demoweb.service.AccountService;


@Controller
@RequestMapping(path = { "/account" })
public class AccountController {
	
	@Autowired
	@Qualifier("accountService")
	private AccountService accountService;
	
	@GetMapping(path = { "/register" })
	public String showRegisterForm(MemberDto member) {
		
		return "account/register";	//	/WEB-INF/views/ + account/register + .jsp
	}
	
	@PostMapping(path = { "/register" })
	public String register(MemberDto member, 
						   BindingResult br) { // @Valid에 의해 검출된 오류 정보가 저장된 객체
		
		if (br.hasErrors()) {
			System.out.println("유효성 검사 오류 발생");
			return "account/register";
		}
		
		// 1. 요청 데이터 읽기 -> DTO에 저장 : 전달인자 사용으로 대체
		System.out.println(member);
		
		// 2. 요청 처리
		accountService.registerMember(member);
		
		// 3. View에서 사용할 수 있도록 데이터 전달
		
		// 4. View 또는 다른 Controller로 이동
		return "redirect:/account/login";
	}
	
	@GetMapping(path = { "/login" })
	public String showLoginForm() {
		
		return "account/login";		//  /WEB-INF/views/ + account/login + .jsp
	}
	
	@PostMapping(path = { "/login" })
	public String login(String memberId, String passwd, HttpSession session, Model model) {
		// 1. 요청 데이터 읽기 ( 전달인자 사용해서 대체 )
		
		// 2. 요청 처리
		MemberDto member = accountService.findMemberByIdAndPasswd(memberId, passwd);
		
		if (member != null) {
			session.setAttribute("loginuser", member);
		} else {
			model.addAttribute("loginfail", memberId);
			return "account/login"; // /WEB-INF/views/ + account/login + .jsp
		}
		
		// 3. View에서 사용하도록 데이터 전달
		
		// 4. View 또는 다른 Controller로 이동 
		return "redirect:/home"; // return "redirect:/home.action";
	}
	
	@GetMapping(path = { "/logout" })
	public View logout(HttpSession session) {
		
		session.removeAttribute("loginuser");
		
		return new RedirectView("login");
	}
	
	

}












