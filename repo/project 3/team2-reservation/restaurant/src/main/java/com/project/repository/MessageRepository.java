package com.project.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.entity.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Integer>{

	MessageEntity findMessageByMsgId(int msgId);

	// messages show & Before 30 days messages do not show
	@Query(value="SELECT * FROM tbl_message WHERE msg_date > TO_CHAR(sysdate-30, 'yyyy-MM-dd') ",
		   nativeQuery = true)
	List<MessageEntity> findAllMessage();
	
	// message confirmed -> message deleted but list show (not completely deleted)
	@Modifying
	@Transactional
	@Query(value="UPDATE TBL_MESSAGE SET msg_deleted = 1 WHERE MSG_ID = :msgId ",
		   nativeQuery = true)
	void deleteMessageInText(@Param("msgId") int msgId);
	
	// message find by userName
	@Query(value="SELECT * FROM tbl_message WHERE msg_name = :msgName ",
		   nativeQuery = true)
	List<MessageEntity> findByName(@Param("msgName") String msgName);
	
	// message statistics
	@Query(value="select a.c_month, count(*) cnt "
			+ "from "
			+ "("
			+ "    SELECT tm.*, extract(MONTH from msg_date) c_month "
			+ "    FROM tbl_message tm "
			+ "    where tm.msg_date > add_months(sysdate, -6) "
			+ ") a "
			+ "group by a.c_month ",
		   nativeQuery = true)
	List<BigDecimal[]> findMessageStatistics();
	
	
	
	
	
	
}
