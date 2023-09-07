package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.web.dao.FoodDAO;
import com.sist.web.entity.FoodEntity;
import com.sist.web.entity.SeoulEntity;

import java.util.*;

@Controller
@RequestMapping("food/")
public class FoodController {
	@Autowired
	private FoodDAO dao;
	
	@RequestMapping("food_find")
	public String food_find(String fd, String page, Model model) {
		if(fd==null)
			fd="마포";
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowsize=20;
		int start=rowsize*curpage-rowsize;
		List<FoodEntity> list=dao.foodFindData(fd, start);
		
		int totalpage=dao.foodFindTotalPage(fd);
		final int BLOCK=10;
		int startpage=(curpage-1)/BLOCK*BLOCK+1;
		int endpage=(curpage-1)/BLOCK*BLOCK+BLOCK;
		if(endpage>totalpage)
			endpage=totalpage;
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("list",list);
		model.addAttribute("fd", fd);
		model.addAttribute("main_html","food/food_find");
		return "main";
	}
}
