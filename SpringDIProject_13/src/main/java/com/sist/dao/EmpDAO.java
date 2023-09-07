package com.sist.dao;
/*
 * servece(interface) => serviceImpl => repository(dao)
 */
import java.util.*;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sist.vo.EmpVO;
public class EmpDAO extends SqlSessionDaoSupport{
/*
 	<resultMap type="com.sist.vo.EmpVO" id="empMap">
 		<result column="dname" property="dvo.dname"/>
 		<result column="loc" property="dvo.loc"/>
 	</resultMap>
 	<sql id="select-emp">
 		SELECT empno,ename,job,TO_CHAR(hiredate,'yyyy-mm-dd') as dbday,sal,dname,loc
 		FROM emp,dept
 		WHERE emp.deptno=dept.deptno
 	</sql>
 	<select id="empdeptListData" resultMap="empMap">
 		<include refid="select-emp"/>
 		ORDER BY sal DESC
 	</select>
 	<select id="empdeptDetailData" resultMap="empMap" parameterType="int">
 		<include refid="select-emp"/>
 		AND empno=#{empno}
 	</select>
 */
	public List<EmpVO> empdeptListData(){
		return getSqlSession().selectList("empdeptListData");
	}
	
	public EmpVO empdeptDetailData(int empno) {
		return getSqlSession().selectOne("empdeptDetailData", empno);
	}
}
