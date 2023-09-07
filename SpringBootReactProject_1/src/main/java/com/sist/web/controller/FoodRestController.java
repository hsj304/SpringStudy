package com.sist.web.controller;
import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("food/")
@CrossOrigin("http://localhost:3000")
public class FoodRestController {
	@Autowired
	private CategoryDAO dao;
	@Autowired
	private FoodDAO fdao;
	@Autowired
	private FoodLocationDAO ldao;
	
	@GetMapping("category1")
	public List<CategoryEntity> food_category1(){
		List<CategoryEntity> list=dao.categoryData1();
		return list;
	}
	
	@GetMapping("category2")
	public List<CategoryEntity> food_category2(){
		List<CategoryEntity> list=dao.categoryData2();
		return list;
	}
	
	@GetMapping("category3")
	public List<CategoryEntity> food_category3(){
		List<CategoryEntity> list=dao.categoryData3();
		return list;
	}
	
	@GetMapping("category_info_react")
	public CategoryEntity category_info(int cno) {
		CategoryEntity vo=(CategoryEntity) dao.findByCno(cno);
		return vo;
	}
	
	@GetMapping("food_list_react")
	public List<FoodEntity> food_list(int cno){
		List<FoodEntity> list=fdao.foodListData(cno);
		return list;
	}
	@GetMapping("food_detail_react")
	public FoodEntity food_detail(int fno) {
		FoodEntity vo=fdao.findByFno(fno);
		return vo;
		
	}
	
	@RequestMapping("food_find_react")
	public List<FoodLocationEntity> food_find(String address, String page){
		if(address==null)
			address = "마포";
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowsize=20;
		int start=rowsize*curpage-rowsize;
		
		List<FoodLocationEntity> list=ldao.foodFindData(address, start);
		return list;
	}
	@GetMapping("food_page_react")
	public PageVO food_page(String address, int page) {
		int totalpage=ldao.foodFindTotalPage(address);
		final int BLOCK=10;
		int startpage=(page-1)/BLOCK*BLOCK+1;
		int endpage=(page-1)/BLOCK*BLOCK+BLOCK;
		
		if(endpage>totalpage)
			endpage=totalpage;
		
		PageVO vo=new PageVO();
		vo.setCurpage(page);
		vo.setTotalpage(totalpage);
		vo.setStartpage(startpage);
		vo.setEndpage(endpage);
		return vo;
	}
}
