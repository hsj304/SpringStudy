package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.SeoulMapper;
import com.sist.vo.SeoulVO;

@Repository
public class SeoulDAO {
	
	@Autowired
	private SeoulMapper mapper;
	
	public List<SeoulVO> seoulLocationData(Map map)
	{
		return mapper.seoulListData(map);
	}
	
	public int seoulLocationTotalPage(Map map)
	{
		return mapper.seoulTotalPage(map);
	}
}
