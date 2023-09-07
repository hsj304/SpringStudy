package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.ReplyMapper;
import com.sist.vo.ReplyVO;

@Repository
public class ReplyDAO {
	@Autowired
	private ReplyMapper mapper;
	
 	public List<ReplyVO> replyListData(int fno){
 		return mapper.replyListData(fno);
 	}
 	
 	public void replyInsert(ReplyVO vo) {
 		mapper.replyInsert(vo);
 	}
 	
 	public void replyDelete(int no) {
 		mapper.replyDelete(no);
 	}
 	
 	public void replyUpdate(ReplyVO vo) {
 		mapper.replyUpdate(vo);
 	}
}
