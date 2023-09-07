package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.RecipeMapper;
import com.sist.vo.RecipeVO;

@Repository
public class RecipeDAO {
	@Autowired
	private RecipeMapper mapper;
	
//	@Select("SELECT no,hit,title,poster,chef,link,num "
//		  + "FROM (SELECT no,hit,title,poster,chef,link,rownum as num "
//		  + "	   FROM (SELECT no,hit,title,poster,chef,link "
//		  + "			 FROM recipe ORDER BY no ASC)) "
//		  + "WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> recipeListData(Map map){
		return mapper.recipeListData(map);
	}
	
//	@Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe")
	public int recipeTotalPage() {
		return mapper.recipeTotalPage();
	}
}
