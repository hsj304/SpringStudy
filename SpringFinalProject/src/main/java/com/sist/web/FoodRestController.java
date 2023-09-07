package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.*;
import com.sist.vo.*;

@RestController
public class FoodRestController {

	@Autowired
	private FoodDAO dao;
	@Autowired
	private ReplyDAO rdao;
	@GetMapping(value="food/category_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_catagory() throws Exception {
		
		List<CategoryVO> list=dao.foodCategoryListData();
		
		// 자동으로 json 변환
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		
		return json;
		
	}
	@GetMapping(value = "food/food_list_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_list(int cno) throws Exception
	{
		List<FoodVO> list=dao.foodListData(cno);
		for(FoodVO vo:list)
		{
			String poster=vo.getPoster();
			poster=poster.substring(0,poster.indexOf("^"));
			poster=poster.replace("#", "&");
			vo.setPoster(poster);
			
			String address=vo.getAddress();
			if(address.contains("지번"))
			{
			address=address.substring(0,address.indexOf("지번"));
			vo.setAddress(address);
			}
			int rcount=rdao.foodReplyCount(vo.getFno());
			if(rcount!=0)
			{
			ReplyVO rvo=rdao.foodReplyData(vo.getFno());
			vo.setUserName(rvo.getName());
			vo.setRdata(rvo.getMsg());
			}
		}
			
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	//////////////카테고리별 맛집 출력
	@GetMapping(value="food/category_info_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_category_info(int cno) throws Exception {
		
		CategoryVO vo=dao.foodCategoryInfoData(cno);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
		
	}
	

	
	@GetMapping(value = "food/food_find_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_find(int page,String column,String fd) throws Exception
	{
		Map map=new HashMap();
		
		map.put("column", column);
		map.put("fd", fd);
		int rowsize=20;
		int start=(rowsize*page)-(rowsize-1);
		int end=rowsize*page;
		map.put("start",start);
		map.put("end", end);
		List<FoodVO> list=dao.foodFindData(map);
		for(FoodVO vo:list)
		{
			String poster=vo.getPoster();
			poster=poster.substring(0,poster.indexOf("^"));
			poster=poster.replace("#", "&");
			vo.setPoster(poster);
		}
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		
		return json;
		
	}
	@GetMapping(value = "food/page_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_page(int page,String column,String fd) throws Exception
	{ 
		Map map=new HashMap();
		map.put("column", column);
		map.put("fd", fd);
		
		int totalpage=dao.foodFindTotalPage(map);
		
		final int BLOCK=10;
		int startpage=((page-1)/BLOCK*BLOCK)+1;
		int endpage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage)
			endpage=totalpage;
		
		PageVO vo=new PageVO();
		vo.setCurpage(page);
		vo.setTotalpage(totalpage);
		vo.setStartpage(startpage);
		vo.setEndpage(endpage);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		
		return json;
	}
	
	@GetMapping(value = "food/food_detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_detail(int fno) throws Exception
	{
		FoodVO vo=dao.foodDetailData(fno);
		String addr=vo.getAddress();
		addr=addr.substring(0,addr.indexOf("지번"));
		vo.setAddress(addr.trim());
		
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		
		return json;
		
	}
	
	
	@GetMapping(value = "food/food_house_detail_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_house_detail(int fno) throws Exception
	{
		FoodVO vo=dao.foodDetailData(fno);
		String addr=vo.getAddress();
		if(addr.contains("지번")) {
		addr=addr.substring(0,addr.indexOf("지번"));
		vo.setAddress(addr.trim());
		}
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		
		return json;
	}
	

}
