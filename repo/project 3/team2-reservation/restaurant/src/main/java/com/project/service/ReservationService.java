package com.project.service;

import java.math.BigDecimal;
import java.util.List;

import com.project.dto.ReservationDto;
import com.project.entity.ReservationEntity;

public interface ReservationService {
	
	public default ReservationDto reservationEntityToDto(ReservationEntity reservationEntity) {
		
		ReservationDto reservationDto = new ReservationDto();
		
		reservationDto.setResId(reservationEntity.getResId());
		reservationDto.setResName(reservationEntity.getResName());
		reservationDto.setResAdult(reservationEntity.getResAdult());
		reservationDto.setResChild(reservationEntity.getResChild());
		reservationDto.setResDate(reservationEntity.getResDate());
		reservationDto.setResEmail(reservationEntity.getResEmail());
		reservationDto.setResTime(reservationEntity.getResTime());
		reservationDto.setResTableId(reservationEntity.getResTableId());
		
		return reservationDto;
	}
	
	public default ReservationEntity reservationDtoToEntity(ReservationDto reservationDto) {
		
		ReservationEntity reservationoEntity = ReservationEntity.builder()
																.resId(reservationDto.getResId())
																.resName(reservationDto.getResName())
																.resAdult(reservationDto.getResAdult())
																.resChild(reservationDto.getResChild())
																.resDate(reservationDto.getResDate())
																.resEmail(reservationDto.getResEmail())
																.resTime(reservationDto.getResTime())
																.resTableId(reservationDto.getResTableId())
																.build();
		
		return reservationoEntity;
	}
	
	public default void reservationDtoToEntity(ReservationDto reservationDto, ReservationEntity entity) {
		
		entity.setResName(reservationDto.getResName());
		entity.setResAdult(reservationDto.getResAdult());
		entity.setResChild(reservationDto.getResChild());
		entity.setResDate(reservationDto.getResDate());
		entity.setResEmail(reservationDto.getResEmail());
		entity.setResTime(reservationDto.getResTime());
		entity.setResTableId(reservationDto.getResTableId());
		
	}
	
	
	void insertByReservation(ReservationDto reservationDto);

	List<ReservationDto> findAll();

	List<ReservationDto> findReservationByName(String resName);

	void deleteByReservationUser(int resId);

	ReservationEntity findRecentReservationByName(String resName);

	ReservationDto findReservationByResId(int resId);

	void getData(ReservationDto reservationDto);

	// Administrator Statistics
	List<BigDecimal[]> selectReservationCount();

	List<ReservationDto> findByDateTime(ReservationDto reservationDto);

	// before ocr test, check reservation info and user reservation info number
//	ReservationDto selectReservationByNameAndTableId(String resName, int resTableId);
	
	

	
}
