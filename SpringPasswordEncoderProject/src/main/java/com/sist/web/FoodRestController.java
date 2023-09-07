package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.FoodDAO;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;

import java.util.*;

import javax.servlet.http.HttpSession;
@RestController
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping(value = "food/food_category_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_category_vue(int cno) throws Exception {
		Map map=new HashMap();
		map.put("cno", cno);
		List<CategoryVO> list=dao.foodCategoryListData(map);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value = "food/food_category_info_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_category_info(int cno) throws Exception {
		CategoryVO vo=dao.foodCategoryInfoData(cno);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
	@GetMapping(value = "food/food_list_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_list(int cno) throws Exception {
		List<FoodVO> list=dao.foodListData(cno);
		for(FoodVO vo:list) {
			String poster=vo.getPoster();
			poster=poster.substring(0, poster.indexOf("^"));
			poster=poster.replace("#", "&");
			vo.setPoster(poster);
			
			String address=vo.getAddress();
			address=address.substring(0,address.indexOf("지번")).trim();
			vo.setAddress(address);
			
		}
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value = "food/food_detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_detail(int fno, HttpSession session) throws Exception {
		String id=(String)session.getAttribute("id");
		FoodVO vo=dao.foodDetailData(fno);
		String addr=vo.getAddress();
		addr=addr.substring(0,addr.indexOf("지번")).trim();
		vo.setAddress(addr);
		vo.setSessionId(id);
		if(vo.getMenu().contains("원")) {
			String menu=vo.getMenu();
			menu=menu.substring(0,menu.lastIndexOf("원"));
			vo.setMenu(menu);
		}
		ObjectMapper mapper=new ObjectMapper();
		return mapper.writeValueAsString(vo);
	}
}
