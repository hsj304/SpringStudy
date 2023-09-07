package com.sist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.dao.SeoulLocationDAO;
import com.sist.web.entity.SeoulLocationEntity;

@Controller
public class SeoulController {
	@Autowired
	private SeoulLocationDAO dao;
	
	@GetMapping("seoul/seoul_location")
	public String seoul_location(Model model, String fd, String page) {
		if(page==null)
			page="1";
		if(fd==null)
			fd="마포";
		int curpage=Integer.parseInt(page);
		int rowsize=12;
		int start=rowsize*(curpage-1);
		
		List<SeoulLocationEntity> list=dao.seoulListData(fd, start);
		
		int totalpage=dao.seoulTotalPage(fd);
		final int BLOCK=10;
		int startpage=(curpage-1)/BLOCK*BLOCK+1;
		int endpage=(curpage-1)/BLOCK*BLOCK+BLOCK;
		if(endpage>totalpage)
			endpage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("fd",fd);
		model.addAttribute("list", list);
		return "seoul/seoul_location";
	}
}
