package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.SeoulVO;

public interface SeoulMapper {
	
	public List<SeoulVO> seoulListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM ${table_name}")
	public int seoulTotalPage(Map map);
	
	
}
