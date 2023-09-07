package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;


@Repository("dao")
public class EmpDAO {
	@Autowired
	private EmpMapper mapper;
	public List<EmpVO> empListData() {
		return mapper.empListData();
	}
	public EmpVO empDetailData(int empno) {
		return mapper.empDetailData(empno);
	}
	public List<EmpVO> empSearchData(String ename){
		return mapper.empSearchData(ename);
	}
}
