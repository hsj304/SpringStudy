package com.sist.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.ReplyDAO;
import com.sist.vo.ReplyVO;

@RestController
public class ReplyRestController {
	@Autowired
	private ReplyDAO dao;
	
	//공통으로 사용
	public String reply_list_data(int fno) {
		String json="";
		try {
			List<ReplyVO> list=dao.replyListData(fno);
			ObjectMapper mapper=new ObjectMapper();
			json = mapper.writeValueAsString(list);
		} catch(Exception e) {}
		return json;
	}
	
	@GetMapping(value = "food/reply_list_vue.do", produces = "text/plain;charset=UTF-8")
	public String reply_list(int fno) {
		return reply_list_data(fno);
	}
	
	@PostMapping(value = "food/reply_insert_vue.do", produces = "text/plain;charset=UTF-8")
	public String reply_insert(ReplyVO vo, HttpSession session) {
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		vo.setId(id);
		vo.setName(name);
		dao.replyInsert(vo);
		
		return reply_list_data(vo.getFno());
	}
	
	@GetMapping(value = "food/reply_delete_vue.do", produces = "text/plain;charset=UTF-8")
	public String reply_delete(int no, int fno) {
		dao.replyDelete(no);
		
		return reply_list_data(fno);
	}
	
	@PostMapping(value = "food/reply_update_vue.do", produces = "text/plain;charset=UTF-8")
	public String reply_update(ReplyVO vo) {
		dao.replyUpdate(vo);
		return reply_list_data(vo.getFno());
	}
}
