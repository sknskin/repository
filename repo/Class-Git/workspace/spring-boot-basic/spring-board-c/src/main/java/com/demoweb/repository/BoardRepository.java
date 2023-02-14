package com.demoweb.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demoweb.entity.BoardAttachEntity;
import com.demoweb.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

	List<BoardEntity> findAllByOrderByBoardNoDesc();
	
	@Query(value = "SELECT * FROM tbl_board ORDER BY board_no DESC LIMIT :from, :count",
		   nativeQuery = true)
	List<BoardEntity> findAllWithPage(@Param("from") int from, @Param("count") int count);	
	long countBy();
	
	BoardEntity findByBoardNo(int boardNo);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE BoardEntity b SET b.readCount = b.readCount + 1 WHERE b.boardNo = :boardNo")
	// @Query(value = "UPDATE tbl_board b SET b.read_count = b.read_count + 1 WHERE b.board_no = :boardNo", navtiveQuery = true)
	void increaseBoardReadCount(@Param("boardNo") int boardNo);
	
	@Query(value = "SELECT ba FROM BoardAttachEntity ba WHERE ba.attachNo = :attachNo")
	BoardAttachEntity findBoardAttachByAttachNo(@Param("attachNo") int attachNo);
	
}





















