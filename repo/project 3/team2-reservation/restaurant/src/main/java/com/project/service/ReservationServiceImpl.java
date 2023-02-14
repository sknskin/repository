package com.project.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

//import com.project.common.Util;
import com.project.dto.ReservationDto;
import com.project.entity.ReservationEntity;
import com.project.repository.ReservationRepository;

@Service("reservationService")
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	
	//예약하기
	@Override
	public void insertByReservation(ReservationDto reservationDto){
		
		ReservationEntity reservationEntity = ReservationEntity.builder()
												   				.resId(reservationDto.getResId())
												   				.resAdult(reservationDto.getResAdult())
												   				.resChild(reservationDto.getResChild())
												   				.resDate(reservationDto.getResDate())
												   				.resTime(reservationDto.getResTime())
												   				.resEmail(reservationDto.getResEmail())
												   				.resName(reservationDto.getResName())
												   				.resTableId(reservationDto.getResTableId())
												   				.build();

		reservationRepository.save(reservationEntity);
		
	}

	
	// 예약 리스트 조회
	@Override
	public List<ReservationDto> findAll() {

		List<ReservationEntity> resList = reservationRepository.findAll(Sort.by(Sort.Direction.ASC, "resId"));
		ArrayList<ReservationDto> resLists = new ArrayList<>();

		for (ReservationEntity reservationEntity : resList) {
			resLists.add(reservationEntityToDto(reservationEntity));
		}

		return resLists;
	}
	
		
	//사용자 예약 조회
	@Override
	public List<ReservationDto> findReservationByName(String resName){
		
		List<ReservationEntity> findResName = reservationRepository.findByResName(resName);
		ArrayList<ReservationDto> findResNameList = new ArrayList<>();
		
		for (ReservationEntity reservationEntity : findResName) {
			findResNameList.add(reservationEntityToDto(reservationEntity));
		}
		
		return findResNameList;
		
	}
	
	
	//사용자 예약 삭제
	@Override
	public void deleteByReservationUser(int resId) {
		
		reservationRepository.deleteById(resId);
	}


	// 첫번째 예약 조회
	@Override
	public ReservationEntity findRecentReservationByName(String resName) {

		ReservationEntity entity = reservationRepository.selectRecentReservationByName(resName);
		
		return entity;
	}


	// 사용자 이름으로 예약 조회
	@Override
	public ReservationDto findReservationByResId(int resId) {
		
		ReservationEntity res = reservationRepository.findById(resId).orElse(null);
		
		ReservationDto resDto = reservationEntityToDto(res);
		
		return resDto;
	}


	// ajax 예약 테이블 조회
	@Override
	public void getData(ReservationDto reservationDto) {

//		ReservationEntity resUpdate = reservationDtoToEntity(reservationDto);		
//		reservationRepository.updateReservation(resUpdate);
		
		ReservationEntity entity = reservationRepository.findById(reservationDto.getResId()).orElse(null);
		
		if (entity != null) {
			reservationDtoToEntity(reservationDto, entity);
			reservationRepository.save(entity);
		}
	}
	
	
	// Administrator Statistics
	@Override
	public List<BigDecimal[]> selectReservationCount() {
		
		List<BigDecimal[]> reservation = reservationRepository.findReservationStatistics();
		
		return reservation;
	}


	// 예약 테이블 조회 시 시간 및 날짜 조회
	@Override
	public List<ReservationDto> findByDateTime(ReservationDto resv) {
		
		List<ReservationEntity> resList = reservationRepository.findByResDateAndResTimeBetween(resv.getResDate(), resv.getResTime() - 1, resv.getResTime() + 1);
		ArrayList<ReservationDto> resLists = new ArrayList<>();

		for (ReservationEntity reservationEntity : resList) {
			resLists.add(reservationEntityToDto(reservationEntity));
		}
		
		return resLists;
	}


	// before ocr test, check reservation info and user reservation info number
//	@Override
//	public ReservationDto selectReservationByNameAndTableId(String resName, int resTableId) {
//
//		ReservationDto resInfo = reservationRepository.findByResNameAndResTableId(resName, resTableId);
//		
//		return resInfo;
//	}


	
}
