package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

import lombok.Delegate;

public interface BoardMapper {
	//목록 출력=>페이징
	@Select("SELECT no,subject,name,TO_CHAR(regdate,'yyyy-mm-dd') as dbday,hit,num "
		  + "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
		  + "	   FROM (SELECT /*+INDEX_DESC(springboard sb_no_pk)*/no,subject,name,regdate,hit "
		  + "			 FROM springboard)) "
		  + "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(Map map);

	@Select("SELECT CEIL(COUNT(*)/10.0) FROM springboard")
	public int boardTotalPage();
	
	@Insert("INSERT INTO springboard(no,name,subject,content,pwd) "
		  + "VALUES(sb_no_seq.nextval,#{name},#{subject},#{content},#{pwd})")
	public void boardInsert(BoardVO vo);
	
	//상세보기
	@Update("UPDATE springBoard SET "
		  + "hit=hit+1 "
		  + "WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no,subject,content,name,TO_CHAR(regdate,'yyyy-mm-dd') as dbday,hit FROM springboard WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	
	//수정하기
	@Select("SELECT no,name,subject,content "
		  + "FROM springboard "
		  + "WHERE no=#{no}")
	public BoardVO boardUpdateData(int no);
	
	@Select("SELECT pwd FROM springboard WHERE no=#{no}")
	public String boardGetPassword(int no);
	
	@Update("UPDATE springboard SET "
		  + "name=#{name},subject=#{subject},content=#{content} "
		  + "WHERE no=#{no}")
	public void boardUpdate(BoardVO vo);
	
	// 삭제
	@Delete("DELETE FROM springboard "
		  + "WHERE no=#{no}")
	public void boardDelete(int no);
}
