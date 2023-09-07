package com.sist.mapper2;
import java.util.*;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.EmpVO;

public interface EmpDeptMapper {
	@Results({
		@Result(column = "dname", property = "dvo.dname"),
		@Result(column = "loc", property = "dvo.loc")
	})
	@Select("SELECT empno,ename,job,TO_CHAR(hiredate,'yyyy-mm-dd') as dbday,sal,dname,loc " + 
			"FROM emp,dept " + 
			"WHERE emp.deptno=dept.deptno "
		  + "ORDER BY sal DESC")
	public List<EmpVO> empdeptListData();
	
	@Results({
		@Result(column = "dname", property = "dvo.dname"),
		@Result(column = "loc", property = "dvo.loc")
	})
	@Select("SELECT empno,ename,job,TO_CHAR(hiredate,'yyyy-mm-dd') as dbday,sal,dname,loc " + 
			"FROM emp,dept " + 
			"WHERE emp.deptno=dept.deptno "
		  + "AND empno=#{empno}")
	public EmpVO empdeptDetailData(int empno);
	

}
