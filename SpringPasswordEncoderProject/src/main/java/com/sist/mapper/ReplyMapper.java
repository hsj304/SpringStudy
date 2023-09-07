package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

public interface ReplyMapper {
 	public List<ReplyVO> replyListData(int fno);
 	
 	public void replyInsert(ReplyVO vo);
 	
 	@Delete("DELETE FROM springTestReply WHERE no=#{no}")
 	public void replyDelete(int no);
 	
 	@Update("UPDATE springTestReply SET "
 		  + "msg=#{msg} "
 		  + "WHERE no=#{no}")
 	public void replyUpdate(ReplyVO vo);
}
