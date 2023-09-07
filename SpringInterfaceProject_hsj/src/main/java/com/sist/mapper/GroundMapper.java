package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.Ground_DetailVO;

public interface GroundMapper {
	@Select("SELECT gno,gname,gaddr,gimage,gnotice,gprice,num "
		  + "FROM (SELECT gno,gname,gaddr,gimage,gnotice,gprice,rownum as num "
		  + "	   FROM (SELECT /*+INDEX_DESC (ground_detail gd_gno_pk)*/gno,gname,gaddr,gimage,gnotice,gprice "
		  + "			 FROM ground_detail "
		  + "			 WHERE gname LIKE '%'||#{gname}||'%')) "
		  + "WHERE num BETWEEN #{start} AND #{end}")
	public List<Ground_DetailVO> groundListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM ground_detail")
	public int groundTotalPage();
}
