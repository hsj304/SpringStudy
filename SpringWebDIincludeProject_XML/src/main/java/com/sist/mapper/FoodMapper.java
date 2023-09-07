package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;


public interface FoodMapper {
	//동적 쿼리를 이용하는 경우
	@Select("<script>"
	        + "SELECT cno, poster, title "
	        + "FROM food_category "
	        + "WHERE "
	        + "<if test=\"cno==1\"> "
	        + "cno BETWEEN 1 AND 12 "
	        + "</if>"
	        + "<if test=\"cno==2\"> "
	        + "cno BETWEEN 13 AND 18 "
	        + "</if>"
	        + "<if test=\"cno==3\"> "
	        + "cno BETWEEN 19 AND 30 "
	        + "</if> "
	        + "ORDER BY cno ASC "
	        + "</script>")
	public List<CategoryVO> foodCategoryData(Map map);
	
	//카테고리 정보 읽기
	@Select("SELECT title,subject "
		  + "FROM food_category "
		  + "WHERE cno=#{cno}")
	public CategoryVO foodCategoryInfoData(int cno);
	
	//카테고리별 맛집 정보 읽기
	@Select("SELECT fno,cno,name,poster,address,score,type,phone "
		  + "FROM food_house "
		  + "WHERE cno=#{cno}")
	public List<FoodVO> foodListData(int cno);
	
	//맛집별 상세보기
	@Select("SELECT fno,cno,name,score,poster,phone,address,type,time,parking,price,menu "
		  + "FROM food_house "
		  + "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	/*
	 * mapper에서는 반드시 매개변수가 1개
	 * 데이터를 많이 첨부할 시에는 VO, Map
	 * 데이터가 VO에 있는 데이터면 => VO 첨부
	 * VO에 없는 변수 첨부시에는 Map
	 * => 동적쿼리를 이용할때는 => Map
	 * => DAO는 사용자가 필요한 내용으로 설정
	 */
}
