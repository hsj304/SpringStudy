package com.sist.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sist.vo.ShopVO;

public class ShopDAO extends SqlSessionDaoSupport {
/*
 	<select id="shopListData" resultType="ShopVO">
		SELECT  no,title,address,msg,rownum
		FROM seoul_location
		WHERE rownum&lt;=10
 	</select>
 	<select id="shopDetailData" resultType="ShopVO" parameterType="int">
 		SELECT no,title,address,msg
 		FROM seoul_location
 		WEHRE no=#{no}
 	</select>
 */
	public List<ShopVO> shopListData(){
		return getSqlSession().selectList("shopListData");
	}
	
	public ShopVO shopDetailData(int no) {
		return getSqlSession().selectOne("shopDetailData", no);
	}
}
