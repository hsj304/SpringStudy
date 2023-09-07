package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.RecipeMapper;
import com.sist.vo.ChefVO;
import com.sist.vo.RecipeVO;

@Repository
public class RecipeDAO {
	@Autowired
	private RecipeMapper mapper;
	
	public List<RecipeVO> recipeListData(Map map)
	{
		return mapper.recipeListData(map);
	}
	
	public int recipeRowCount()
	{
		return mapper.recipeRowCount();
	}
	
	public List<ChefVO> chefListData(Map map)
	{
		return mapper.chefListData(map);
	}
	
	public int chefTotalpage()
	{
		return mapper.chefTotalpage();
				
	}
	
	public ChefVO chefInfoData(String chef)
	{
		return mapper.chefInfoData(chef);
	}
	
	public List<RecipeVO> chefFindData(Map map)
	{
		return mapper.chefFindData(map);
	}
	public int chefFindCount(Map map)
	{
		return mapper.chefFindCount(map);
	}
	
	
}
