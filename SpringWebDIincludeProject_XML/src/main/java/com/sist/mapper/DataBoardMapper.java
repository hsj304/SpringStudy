package com.sist.mapper;
import com.sist.vo.*;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface DataBoardMapper {
	@Select("SELECT no,subject,name,TO_CHAR(regdate,'yyyy-mm-dd') as dbday,hit,num "
		  + "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
		  + "	   FROM (SELECT /*+INDEX_DESC(springdataboard sdb_no_pk)*/no,subject,name,regdate,hit "
		  + "			  FROM springdataboard)) "
		  + "WHERE num BETWEEN #{start} AND #{end}")
	public List<DataBoardVO> databoardListData(Map map);
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM springdataboard")
	public int databoardTotalPage();
	@SelectKey(keyProperty = "no", resultType = int.class, before = true, statement = "SELECT NVL(MAX(no)+1,1) as no FROM springdataboard")
	@Insert("INSERT INTO springdataboard(no,name,subject,content,pwd,filename,filesize,filecount) "
		  + "VALUES(#{no},#{name},#{subject},#{content},#{pwd},#{filename},#{filesize},#{filecount})")
	public void databoardInsert(DataBoardVO vo);
	
	//상세보기
	@Update("UPDATE springdataboard SET "
		 +  "hit = hit+1 "
		  + "WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no,name,subject,content,pwd,hit,TO_CHAR(regdate,'yyyy-mm-dd') as dbday,filename,filesize,filecount "
		  + "FROM springdataboard "
		  + "WHERE no=#{no}")
	public DataBoardVO databoardDetailData(int no);
	
	//찾기
	@Select("SELECT no,subject,name,TO_CHAR(regdate,'yyyy-mm-dd') as dbday,hit "
		  + "FROM springdataboard "
		  + "WHERE ${fs} LIKE '%'||#{ss}||'%'")
	//$=>컬럼명, 테이블명, # => 일반 데이터
	// name              'name'
	public List<DataBoardVO> databoardFindData(Map map);
	
	//비밀번호 검색
	@Select("SELECT pwd FROM springdataboard WHERE no=#{no}")
	public String databoardGetPassword(int no);
	
	//수정
	@Update("UPDATE springdataboard SET "
		  + "name=#{name},subject=#{subject},content=#{content} "
		  + "WHERE no=#{no}")
	public void databoardUpdate(DataBoardVO vo);
	
	//삭제
	@Delete("DELETE FROM springdataboard WHERE no=#{no}")
	public void databoardDelete(int no);
}
