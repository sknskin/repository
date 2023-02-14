package com.project.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.entity.ReservationEntity;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {

	
	@Query(value="SELECT * FROM tbl_reservation WHERE res_name = :resName ORDER BY res_id ASC ", 
		   nativeQuery = true)
	List<ReservationEntity> findByResName(@Param("resName") String resName);
	
	
	@Query(value="SELECT * FROM (SELECT * FROM tbl_reservation WHERE res_name= :resName ORDER BY res_id DESC) WHERE rownum <= 1 ",
		   nativeQuery = true)
	ReservationEntity selectRecentReservationByName(@Param("resName") String resName);
	
	
	// Administrator Statistics
	@Query(value="select a.c_month, count(*) "
			+ "from "
			+ "( "
			+ "    SELECT tr.*, extract(MONTH from res_date) c_month "
			+ "    FROM tbl_reservation tr "
			+ "    where tr.res_date > add_months(sysdate, -6) "
			+ ") a "
			+ "group by a.c_month ",
		   nativeQuery = true)
	List<BigDecimal[]> findReservationStatistics();


	List<ReservationEntity> findByResDateAndResTimeBetween(Date resDate, int resTimeFrom, int resTimeTo);


	// before ocr test, check reservation info and user reservation info number
//	ReservationDto findByResNameAndResTableId(String resName, int resTableId);
	
}
