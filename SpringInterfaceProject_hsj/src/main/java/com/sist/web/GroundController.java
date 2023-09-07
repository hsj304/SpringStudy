package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.GroundDAO;

@Controller
public class GroundController {
	@Autowired
	private GroundDAO dao;
	
	@GetMapping("main/list.do")
	public String ground_list() {
		
		return "main/list.jsp";
	}
}
