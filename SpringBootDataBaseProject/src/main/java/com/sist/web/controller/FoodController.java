package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.service.*;
import com.sist.web.vo.*;
import java.util.*;


@Controller
public class FoodController {
	@Autowired
	private FoodService service;
	
	@GetMapping("/")
	public String food_category(Model model) {
		List<CategoryVO> list=service.categoryListData();
		model.addAttribute("list",list);
		return "category";
	}
	
}
