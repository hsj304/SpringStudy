package com.sist.dao;
import java.util.*;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class SeoulDAO extends SqlSessionDaoSupport {
	/*
	<select id="seoulListData" resultType="SeoulLocationVO" parameterType="hashmap">
		SELECT no.title,address,num 
		FROM (SELECT no,title,address rownum as num 
			  FROM (SELECT no,title,address 
			  		FROM seoul_location 
			  		ORDER BY no ASC))
		WHERE num BETWEEN #{start} AND #{end}		
		<!-- #{start} map.get("start")
			Map map=new HashMap()
			map.put("start", 1)
			map.put("end", 10)
		 -->
	</select> 	
	 */
	public List<SeoulLocationVO> seoulListData(Map map){
		return getSqlSession().selectList("seoulListData",map);
	}
	
	/*
	<select id="seoulTotalPage" resultType="int">
		SELECT CEIL(COUNT(*)/10.0) FROM seoul_location
	</select>
	 */
	public int seoulTotalPage() {
		return getSqlSession().selectOne("seoulTotalPage");
	}
	
	/*
	<select id="seoulFindData" resultType="SeoulLocationVO" parameterType="String">
		SELECT no,title,address,msg 
		FROM seoul_location
		WHERE title LIKE '%'||#{title}||'%'
	</select>
	 */
	public List<SeoulLocationVO> seoulFindData(String title) {
		return getSqlSession().selectList("seoulFindData",title);
	}
}
