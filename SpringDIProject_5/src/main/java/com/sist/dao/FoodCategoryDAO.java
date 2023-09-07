package com.sist.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class FoodCategoryDAO extends SqlSessionDaoSupport {
	/*
	<select id="foodCategoryListData" resultType="FoodCategoryVO">
 		SELECT cno,title,subject,poster,link
 		FROM food_category
 	</select>
	 */
	public List<FoodCategoryVO> foodCategoryListData(){
		return getSqlSession().selectList("foodCategoryListData");
	}
}
