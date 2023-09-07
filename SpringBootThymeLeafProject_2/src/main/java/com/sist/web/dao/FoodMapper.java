package com.sist.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.sist.web.vo.*;
import java.util.*;

@Repository
@Mapper
public interface FoodMapper {
//	<select id="foodFindData" resultType="FoodVO" parameterType="hashmap">
	public List<FoodVO> foodFindData(Map map);
//	<select id="foodFindTotalPage" resultType="int" parameterType="string">
	public int foodFindTotalPage(String fd);
}
