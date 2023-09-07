package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller
@RequestMapping("board/")
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	//목록
	@GetMapping("list.do")
	public String board_list(String page, Model model) {
		//Model model => 전송 객체(request대신 사용)
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		map.put("start", start);
		map.put("end", end);
		List<BoardVO> list=dao.boardListData(map);
		int totalpage=dao.boardTotalPage();
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list",list);
		
		return "board/list";
	}
	
	//데이터 추가
	@GetMapping("insert.do")
	public String board_insert() {
		
		return "board/insert";
	}
	@PostMapping("insert_ok.do")
	public String board_insert_ok(BoardVO vo) {
		dao.boardInsert(vo);
		return "redirect:list.do";
	}
	//데이터 수정
	@GetMapping("update.do")
	public String board_update(int no, Model model) {
		BoardVO vo=dao.boardUpdateData(no);
		
		model.addAttribute("vo", vo);
		return "board/update";
	}
	
	//데이터 삭제
	@GetMapping("delete.do")
	public String board_delete(int no, Model model) {
		model.addAttribute("no", no);
		return "board/delete";
	}
	
	@PostMapping("delete_ok.do")
	public String board_delete_ok(int no, String pwd,Model model) {
		boolean bCheck=dao.boardDelete(no, pwd);
		model.addAttribute("bCheck", bCheck);
		return "board/delete_ok";
	}
	
	//상세보기
	@GetMapping("detail.do")
	public String board_detail(int no, Model model) {
		BoardVO vo=dao.boardDetailData(no);
		model.addAttribute("vo",vo);
		return "board/detail";
	}
	/*
	 * 	1. Spring MVC
	 * 		1) DispatcherServlet 등록 => web.xml
	 * 		=> 클래스를 등록한 파일 셋팅(클래스 관리)
	 */
	//검색 ==> 동적쿼리
}
