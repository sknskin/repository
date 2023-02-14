package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.dto.ReservationDto;
import com.project.entity.ReservationEntity;
import com.project.service.ReservationService;

@Controller
public class ReservationController {
	
	@Autowired
	@Qualifier("reservationService")
	private ReservationService reservationService;
	
	// 예약하기
	@PostMapping(path={"/reservation/book"}) 
	public String reservationList(ReservationDto reservationDto, Model model, String resName) {

		reservationService.insertByReservation(reservationDto);
		
		ReservationEntity listByName = reservationService.findRecentReservationByName(resName);		

		model.addAttribute("listByName", listByName);
		
		return "reservation/reservation_user_Info";
	}
	
	// 사용자 예약조회
	@PostMapping(path={ "/reservation-by-name" })
	public String reservationListByName(Model model, String resName) {

		List<ReservationDto> listByName = reservationService.findReservationByName(resName);		

		model.addAttribute("listByName", listByName);
		
		return "reservation/userReservation";
	}
	
	// 예약리스트 조회
	@GetMapping(path = { "/admin/adminReservation" })
	public String adminReservation(ReservationDto reservationDto, Model model) {

		List<ReservationDto> lists = reservationService.findAll();

		model.addAttribute("lists", lists);
		
		return "admin/adminReservation";
	}
	
	// 사용자 예약페이지 삭제
	@PostMapping(path= { "/deleteReservation" })
	public String deleteReservation(int resId) {
		
		reservationService.deleteByReservationUser(resId);
		return "reservation/reservation";
	}
	
	// 사용자 예약페이지 수정
	@PostMapping(path = { "/getReservationData" })
	@ResponseBody
	public ReservationDto reservationData(int resId) {
		
		ReservationDto reservation = reservationService.findReservationByResId(resId);
		
		return reservation;
	}
	
	// 예약 수정
	@PostMapping(path= { "/modifyBook" })
	public String reservationFormData(Model model, String resName, ReservationDto reservationDto) {
		
		reservationService.getData(reservationDto);	
		
		List<ReservationDto> listByName = reservationService.findReservationByName(resName);		

		model.addAttribute("listByName", listByName);
		
		return "reservation/userReservation";
	}
	
	// 테이블 중복 확인
	@PostMapping(path = { "/overlap" })
	@ResponseBody
	public List<ReservationDto> reservationCheck(ReservationDto reservationDto, Model model) {

		List<ReservationDto> checkList = reservationService.findByDateTime(reservationDto);
		
		return checkList;
	}
	
	
	@PostMapping(path = { "/checkOverlap" })
	@ResponseBody
	public List<ReservationDto> Check(ReservationDto reservationDto, Model model) {

		List<ReservationDto> check = reservationService.findByDateTime(reservationDto);

		return check;
	}

	  
	  
	
}
