package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.GroundMapper;
import com.sist.vo.Ground_DetailVO;

@Repository
public class GroundDAO {
	@Autowired
	private GroundMapper mapper;
	
//	@Select("SELECT gno,gname,gaddr,gimage,gnotice,gprice,num "
//		  + "FROM (SELECT gno,gname,gaddr,gimage,gnotice,gprice,rownum as num "
//		  + "	   FROM (SELECT /*+INDEX_DESC (ground_detail gd_gno_pk)*/gno,gname,gaddr,gimage,gnotice,gprice "
//		  + "			 FROM ground_detail)) "
//		  + "WHERE num BETWEEN #{start} AND #{end}")
	public List<Ground_DetailVO> groundListData(Map map) {
		return mapper.groundListData(map);
	}
	
//	@Select("SELECT CEIL(COUNT(*)/12.0) FROM gound_detail")
	public int groundTotalPage() {
		return mapper.groundTotalPage();
	}
}
