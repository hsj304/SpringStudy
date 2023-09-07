package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sist.mapper.*;
import com.sist.vo.*;

import oracle.net.aso.b;

@Repository
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
//	@Select("SELECT no,subject,name,TO_CHAR(regdate,'yyyy-mm-dd'),hit,num "
//			  + "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
//			  + "	   FROM (SELECT /*+INDEX_DESC(springboard sb_no_pk)*/no,subject,name,regdate,hit "
//			  + "			 FROM springboard)) "
//			  + "WHERE num BETWEEN #{start} AND #{end}")
		public List<BoardVO> boardListData(Map map){
			return mapper.boardListData(map);
		}
//		
//		@Insert("INSERT INTO springboard(no,name,subject,content,pwd) "
//			  + "VALUES(sb_no_seq.nextval,#{name},#{subject},#{content},#{pwd})")
		public void boardInsert(BoardVO vo) {
			mapper.boardInsert(vo);
		}
	
		public int boardTotalPage() {
			return mapper.boardTotalPage();
		}
		
		
//		@Update("UPDATE springBoard SET "
//			  + "hit=hit+1 "
//			  + "WHERE no=#{no}")
//		@Select("SELECT * FROM springboard WHERE no=#{no}")
		public BoardVO boardDetailData(int no) {
			mapper.hitIncrement(no);
			return mapper.boardDetailData(no);
		}
	
//		@Select("SELECT no,name,subject,content "
//				  + "FROM springboard "
//				  + "WHERE no=#{no}")
		public BoardVO boardUpdateData(int no) {
			return mapper.boardUpdateData(no);
		}
		
//		@Select("SELECT pwd FROM springboard WHERE no=#{no}")
//		@Update("UPDATE springboard SET "
//			  + "name=#{name},subject=#{subject},content=#{content} "
//			  + "WHERE no=#{no}")
		public boolean boardUpdate(BoardVO vo) {
			boolean bCheck=false;
			String db_pwd=mapper.boardGetPassword(vo.getNo());
			if(db_pwd.equals(vo.getPwd())) {
				bCheck=true;
				mapper.boardUpdate(vo);
			}
			
			return bCheck;
		}
		
//		@Delete("DELETE FROM springboard "
//				  + "WHERE no=#{no}")
		public boolean boardDelete(int no, String pwd) {
			boolean bCheck=false;
			String db_pwd=mapper.boardGetPassword(no);
			if(db_pwd.equals(pwd)) {
				bCheck=true;
				mapper.boardDelete(no);
			}
			
			return bCheck;
		}
		
		

}
