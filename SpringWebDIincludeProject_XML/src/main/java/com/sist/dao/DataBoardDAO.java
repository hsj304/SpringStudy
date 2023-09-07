package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.DataBoardMapper;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class DataBoardDAO {
	@Autowired
	private DataBoardMapper mapper;
	
	public List<DataBoardVO> databoardListData(Map map){
		return mapper.databoardListData(map);
	}
	
	public int databoardTotalPage() {
		return mapper.databoardTotalPage();
	}
	
	public void databoardInsert(DataBoardVO vo) {
		mapper.databoardInsert(vo);
	}
	
	public DataBoardVO databoardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.databoardDetailData(no);
	}
	
	public List<DataBoardVO> databoardFindData(Map map){
		return mapper.databoardFindData(map);
	}
	
	public DataBoardVO databoardUpdateData(int no) {
		return mapper.databoardDetailData(no);
	}
	
	public boolean databoardUpdate(DataBoardVO vo) {
		boolean bCheck=false;
		
		String db_pwd=mapper.databoardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			bCheck=true;
			mapper.databoardUpdate(vo);
		}
		
		return bCheck;
	}
	
	public boolean databoardDelete(int no, String pwd) {
		boolean bCheck=false;
		String db_pwd=mapper.databoardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			bCheck=true;
			mapper.databoardDelete(no);
		}
		return bCheck;
	}
}
