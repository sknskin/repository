package com.demoweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.demoweb.dto.BoardCommentDto;

@Mapper
public interface BoardCommentMapper {
	
	@Insert("INSERT INTO boardcomment (boardno, writer, content, groupno, step, depth) " +
			"VALUES (#{ boardNo }, #{ writer }, #{ content }, -1, 1, 0)")
	@Options(useGeneratedKeys = true, keyColumn = "commentno", keyProperty = "commentNo")
	void insertComment(BoardCommentDto comment);
	
	@Update("UPDATE boardcomment " +
			"SET groupNo = #{ groupNo } " +
			"WHERE commentNo = #{ commentNo }")
	void updateGroupNo(@Param("commentNo")int commentNo, @Param("groupNo")int groupNo);
	
	@Select("SELECT commentno, writer, content, regdate, deleted, groupno, step, depth " +
			"FROM boardcomment " +
			"WHERE boardno = #{ boardNo } " +
			"ORDER BY groupno DESC, step ASC")
	List<BoardCommentDto> selectCommentByBoardNo(int boardNo);

	@Update("UPDATE boardcomment " +
			"SET deleted = TRUE " +
			"WHERE commentno = #{ commentNo }")
	void deleteComment(int commentNo);

	@Update("UPDATE boardcomment " +
			"SET content = #{ content } " +
			"WHERE commentno = #{ commentNo }")
	void updateComment(BoardCommentDto comment);

	@Select("SELECT commentno, boardno, writer, content, regdate, groupno, step, depth " + 
			"FROM boardcomment " +
			"WHERE commentno = #{ commentNo }")
	BoardCommentDto selectCommentByCommentNo(int commentNo);

	@Update("UPDATE boardcomment " +
			"SET step = step + 1 " +
			"WHERE groupno = #{ groupNo } and step > #{ step }")
	void updateStepNo(@Param("groupNo") int groupNo, @Param("step") int step);

	@Insert("INSERT INTO boardcomment (boardno, writer, content, groupno, step, depth) " +
			"VALUES (#{ boardNo }, #{ writer }, #{ content }, #{ groupNo }, #{ step }, #{ depth })")
	void insertReComment(BoardCommentDto commentDto);

}

















