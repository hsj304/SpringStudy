package com.sist.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.RecipeDAO;
import com.sist.vo.RecipeVO;

@Service
public class RecipeServiceImpl implements RecipeService {
	@Autowired
	private RecipeDAO dao;
	
	@Override
	public List<RecipeVO> recipeListData(Map map) {
		// TODO Auto-generated method stub
		return dao.recipeListData(map);
	}

	@Override
	public int recipeTotalPage() {
		// TODO Auto-generated method stub
		return dao.recipeTotalPage();
	}
	
}
