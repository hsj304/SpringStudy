package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.FoodMapper;
import com.sist.web.vo.FoodVO;

@Service
public class FoodServiceImpl implements FoodService {
	@Autowired
	private FoodMapper mapper;
	
	@Override
	public List<FoodVO> foodFindData(Map map) {
		// TODO Auto-generated method stub
		return mapper.foodFindData(map);
	}

	@Override
	public int foodFindTotalPage(String fd) {
		// TODO Auto-generated method stub
		return mapper.foodFindTotalPage(fd);
	}

}
