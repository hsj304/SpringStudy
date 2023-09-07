package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.mapper.FoodMapper;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;

@Repository
public class FoodDAO {

	@Autowired
	private FoodMapper mapper;
	
	public List<CategoryVO> foodCategoryListData() {
		return mapper.foodCategoryListData();
	}
	
	/*
	 * @Select("SELECT title,subject FROM food_category " + "WHERE cno=#{cno}")
	 */
	public CategoryVO foodCategoryInfoData(int cno) {
		return mapper.foodCategoryInfoData(cno);
	}
	
	public List<FoodVO> foodListData(int cno)
	{
		return mapper.foodListData(cno);
	}
	
	public List<FoodVO> foodFindData(Map map)
	{
		return mapper.foodFindData(map);
	}
	
	public int foodFindTotalPage(Map map)
	{
		return mapper.foodFindTotalPage(map);
	}
	
	public FoodVO foodDetailData(int fno)
	{
		return mapper.foodDetailData(fno);
	}
	
	public FoodVO foodDetailHouseData(int fno)
	{
		return mapper.foodDetailHouseData(fno);
	}
}
