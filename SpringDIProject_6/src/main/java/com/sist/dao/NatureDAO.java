package com.sist.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sist.vo.NatureVO;

public class NatureDAO extends SqlSessionDaoSupport {
	/*
 	<select id="natureListData" resultType="NatureVO">
 		SELECT no,title,address,msg,rownum
 		FROM seoul_location 
 		WHERE rownum&lt;=10
 	</select>
 	<select id="natureDetailData" resultType="NatureVO" parameterType="int">
 		SELECT no,title,address,msg
 		FROM seoul_location
 		WHERE no=#{no}
 	</select>
	 */
	public List<NatureVO> natureListData(){
		return getSqlSession().selectList("natureListData");
	}
	public NatureVO natureDetailData(int no) {
		return getSqlSession().selectOne("natureDetailData", no);
	}
}
