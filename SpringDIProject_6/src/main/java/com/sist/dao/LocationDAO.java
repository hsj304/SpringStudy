package com.sist.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.sist.vo.*;

public class LocationDAO extends SqlSessionDaoSupport {
	/*
 	<sql id="select-sql">
 		SELECT no,title,address,msg
 		FROM seoul_location
 	</sql>
 	<select id="LocationListData" resultType="LocationVO">
		<include refid="select-sql"/>
 		ORDER BY no ASC
 	</select>
 	<select id="locationDetailData" resultType="LocationVO" parameterType="int">
		<include refid="select-sql"/>
 		WHERE no=${no}
 	</select>
	 */
	public List<LocationVO> LocationListData(){
		return getSqlSession().selectList("LocationListData");
	}
	public LocationVO locationDetailData(int no) {
		return getSqlSession().selectOne("locationDetailData", no);
	}
}
