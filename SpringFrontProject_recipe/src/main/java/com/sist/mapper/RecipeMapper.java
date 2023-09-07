package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.RecipeVO;

public interface RecipeMapper {
	@Select("SELECT no,hit,title,poster,chef,link,num "
		  + "FROM (SELECT no,hit,title,poster,chef,link,rownum as num "
		  + "	   FROM (SELECT no,hit,title,poster,chef,link "
		  + "			 FROM recipe ORDER BY no ASC)) "
		  + "WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> recipeListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe")
	public int recipeTotalPage();
}
