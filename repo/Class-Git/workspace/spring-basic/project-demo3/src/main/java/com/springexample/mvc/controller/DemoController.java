package com.springexample.mvc.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springexample.mvc.dto.IrisDto;

@Controller
public class DemoController {
	
	@GetMapping("/summer-note-demo")
	public String showSummerNote() {
		
		return "summer-note";	 // /WEB-INF/view/ + showtime + .jsp
	}
	
	@PostMapping("/summernote-write")
	public String write(String title, String content, Model model) {
		
		System.out.println(title);
		System.out.println(content);
		
		model.addAttribute("title", title);
		model.addAttribute("content", content);
		
		return "summer-note-result";
	}
	
	@PostMapping("/upload-image-file")
	@ResponseBody // return 값이 jsp 이름이 아니고 응답 데이터. 주로 ajax 응답으로 사용
	// public String uploadImage(MultipartFile file) {
	public String uploadImage(MultipartHttpServletRequest req) {
		
		MultipartFile file = req.getFile("file");
		String fileName = file.getOriginalFilename();
		String unique_file_name = UUID.randomUUID().toString();
		unique_file_name += fileName.substring(fileName.lastIndexOf("."));
		String path = 
				req.getServletContext().getRealPath("/resources/image-files/" + unique_file_name);
		try {
			file.transferTo(new File(path)); // 저장
		} catch (Exception ex) {
			ex.printStackTrace();
		}		 
		
		return "/spring-mvc-1/resources/image-files/" + unique_file_name; // 서버에 저장된 파일 경로
	}
	
	@GetMapping(path = { "/chartjs-demo" })
	public String showChartjsDemo() {
		
		return "chartjs";
	}
	
	@GetMapping(path = { "/load-iris-dataset" })
	@ResponseBody
	public ArrayList<IrisDto> loadIrisDataset(HttpServletRequest req) {
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		ArrayList<IrisDto> irisList = new ArrayList<>();
		
		try {
			String irisPath = req.getServletContext().getRealPath("/data-files/iris.csv");
			fis = new FileInputStream(irisPath);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			br.readLine();
			while (true) {
				String line = br.readLine();
				if (line == null) break;
				
				String[] data = line.split(",");
				IrisDto iris = new IrisDto();
				iris.setSepalLength(Double.parseDouble(data[0]));
				iris.setSepalWidth(Double.parseDouble(data[1]));
				iris.setPetalLength(Double.parseDouble(data[2]));
				iris.setPetalWidth(Double.parseDouble(data[3]));
				iris.setSpecies(data[4]);
				
				irisList.add(iris);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { br.close(); } catch (Exception ex) {}
			try { isr.close(); } catch (Exception ex) {}
			try { fis.close(); } catch (Exception ex) {}
		}
		
		return irisList;
	}
	
	@GetMapping(path = { "/login" })
	public String showLoginView() {
		
		return "login";
	}
	
	@GetMapping(path = { "/naver-login-callback" })
	public String showNaverLoginCallbackView() {
		
		return "naver-login-callback";
	}
	
	@GetMapping(path = { "/naver-login-success" })
	public String naverLoginSuccess(String id, String email, HttpSession session) {
		
		session.setAttribute("loginuser", email);
		
		return "redirect:/home";
	}
	
	@GetMapping(path = { "/redirect-attributes" })
	public String redirectAttributes(RedirectAttributes redirectAttributes) {
		// RedirectAttributes 전달인자는 Redirect 할 때 redirect target controller로
		// 데이터를 전달하는 도구

		redirectAttributes.addFlashAttribute("b", new Date()); // 주소에 표시 X ( 모든 객체 )
		redirectAttributes.addAttribute("a", Math.random()); // 주소에 표시 ( 제한된 객체 )
		
		return "redirect:redirect-target";
	}	
	@GetMapping(path = { "/redirect-target" })
	public String redirectTarget(
			@ModelAttribute("b") Date b,
			// @ModelAttribute("a") double a,
			Model model) {
		
		//model.addAttribute("a2", a);
		model.addAttribute("b2", b);
		
		return "redirect-target";
	}
	
	@GetMapping(path = { "/send-mail" })
	public String showSendMailForm() {
		
		return "send-mail";
	}
	
	@Autowired
	@Qualifier("mailSender")
	private JavaMailSenderImpl mailSender;
	
	@PostMapping(path = { "/send-mail" })
	public String sendMail(String title, String from, String to, String content) {
		
		//for test
		String[] toList = { to, "shared.repo.z@gmail.com" };
		
		// 메일 전송 구현
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message);
			
			messageHelper.setFrom(from);
			// messageHelper.setTo(to);
			messageHelper.setTo(toList);
			messageHelper.setSubject(title);
			messageHelper.setText(content, true);
			
			mailSender.send(message);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return "send-mail";
	}

}















