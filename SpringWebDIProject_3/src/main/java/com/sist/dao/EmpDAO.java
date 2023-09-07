package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.EmpVO;


@Repository
public class EmpDAO extends SqlSessionDaoSupport {
	public List<String> empGetNameData(){
		return getSqlSession().selectList("empGetNameData");
	}
	public List<EmpVO> empInfoData(Map map){
		return getSqlSession().selectList("empInfoData", map);
	}
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
}
