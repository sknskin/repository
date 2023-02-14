package com.demoweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardDto;

@Mapper
public interface BoardMapper {
	
	
	@Insert("INSERT INTO board (title, writer, content) " +
			"VALUES (#{ title }, #{ writer }, #{ content })")
	@Options(useGeneratedKeys = true, keyColumn = "boardno", keyProperty = "boardNo")
//	@SelectKey(statement = "SELECT LAST_INSERT_ID()", 
//			   resultType = Integer.class, keyProperty = "boardNo", before = false)
	void insertBoard(BoardDto board);
	
	@Select("SELECT boardno, title, writer, readcount, regdate, deleted " +
			"FROM board " +
			"ORDER BY boardno DESC " +
			"LIMIT #{ from },#{ count }")
	List<BoardDto> selectBoardByPage(@Param("from")int from, @Param("count")int count);
	
	@Select("SELECT boardno, title, writer, content, readcount, regdate " +
			"FROM board " +
			"WHERE boardno = #{ boardNo } AND deleted = FALSE")
	BoardDto selectBoardByBoardNo(int boardNo);
	
	@Select("SELECT attachno, boardno, userfilename, savedfilename, downloadcount " +
			"FROM boardattach " +
			"WHERE boardno = #{ boardNo } ")
	List<BoardAttachDto> selectBoardAttachByBoardNo(int boardNo);
	
	@Select("SELECT b.boardno, b.title, b.writer, b.content, b.readcount, b.regdate, " +
			"		ba.attachno, ba.userfilename, ba.savedfilename, ba.downloadcount " +
			"FROM board b " +
			"LEFT OUTER JOIN boardattach ba " +
			"ON b.boardno = ba.boardno " +
			"WHERE b.boardno = #{ boardNo } AND deleted = FALSE")
	@Results(id = "boardResultMap",
			 value = {
					 @Result(column = "boardno", property = "boardNo", id = true),
					 @Result(column = "title", property = "title"),
					 @Result(column = "writer", property = "writer"),
					 @Result(column = "content", property = "content"),
					 @Result(column = "readcount", property = "readCount"),
					 @Result(column = "regdate", property = "regDate"),
//					 @Result(column = "boardno", property = "attachments", 
//					 		 many = @Many(select = "selectBoardAttachByBoardNo"))
					 @Result(column = "boardno", property = "attachments", 
			 		 		 many = @Many(resultMap = "boardAttachResultMap"))
			 })
	BoardDto selectBoardByBoardNo2(int boardNo);
	
	@Select("SELECT attachno, boardno, userfilename, savedfilename, downloadcount " +
			"FROM boardattach " +
			"WHERE boardno = #{ boardNo } ")
	@Results(id = "boardAttachResultMap",
			 value = {
					 @Result(column = "attachno", property = "attachNo", id = true),
					 @Result(column = "boardno", property = "boardNo"),
					 @Result(column = "userfilename", property = "userFileName"),
					 @Result(column = "savedfilename", property = "savedFileName"),
					 @Result(column = "downloadcount", property = "downloadCount")
			 })
	List<BoardAttachDto> selectBoardAttachByBoardNo2(int boardNo);

}














