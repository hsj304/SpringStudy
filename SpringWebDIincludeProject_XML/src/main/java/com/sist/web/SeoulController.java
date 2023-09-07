package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.*;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;

@Controller
public class SeoulController {
	@Autowired
	private SeoulDAO dao;
	
	@GetMapping("seoul/location.do")
	public String seoul_location(String page, Model model) {
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<SeoulVO> list=dao.seoulLocationListData(map);
		
		int totalpage=dao.seoulLocationTotalPage();
		
		final int BLOCK=5;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) {
			endpage=totalpage;
		}
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("main_jsp", "../seoul/location.jsp");
		return "main/main";
	}
	
	@GetMapping("seoul/nature.do")
	public String seoul_nature(String page, Model model) {
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=rowSize*curpage-(rowSize-1);
		int end=rowSize*curpage;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<SeoulVO> list=dao.seoulNatureListData(map);
		int totalpage=dao.seoulNatureTotalPage();
		
		final int BLOCK=5;
		int startpage=(curpage-1)/BLOCK*BLOCK+1;
		int endpage = (curpage-1)/BLOCK*BLOCK+BLOCK;
		if(endpage>totalpage) 
			endpage=totalpage;

		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("main_jsp", "../seoul/nature.jsp");
		return "main/main";
	}
	
	@GetMapping("seoul/shop.do")
	public String seoul_shop(String page, Model model) {
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=rowSize*curpage-(rowSize-1);
		int end=rowSize*curpage;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<SeoulVO> list=dao.seoulShopListData(map);
		int totalpage=dao.seoulShopTotalpage();
		
		final int BLOCK=5;
		int startpage=(curpage-1)/BLOCK*BLOCK+1;
		int endpage=(curpage-1)/BLOCK*BLOCK+BLOCK;
		if(endpage>totalpage)
			endpage=totalpage;
		
		model.addAttribute("list", list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("main_jsp", "../seoul/shop.jsp");
		return "main/main";
	}
}
