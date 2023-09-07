package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.SeoulDAO;
import com.sist.vo.PageVO;
import com.sist.vo.SeoulVO;

@RestController
public class SeoulRestController {
	@Autowired
	private SeoulDAO dao;
	private String[] tables= {"","seoul_location","seoul_nature","seoul_shop"};
	
	@GetMapping(value = "seoul/seoul_list_vue.do",produces = "text/plain;charset=UTF-8")
	public String seoulListData(int page,int type) throws Exception
	{
		Map map=new HashMap();
		int start= (20*page)-19;
		int end=20*page;
		map.put("start",start);
		map.put("end",end);
		map.put("table_name",tables[type]);
		List<SeoulVO> list=dao.seoulLocationData(map);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
		
	}
	@GetMapping(value = "seoul/seoul_page_info_vue.do", produces = "text/plain;charset=UTF-8")
	public String seoul_pageInfo(int page,int type) throws Exception
	{
		Map map=new HashMap();
		map.put("table_name",tables[type]);
		int totalpage=dao.seoulLocationTotalPage(map);
		
		PageVO vo=new PageVO();
		final int BLOCK=5;
		int startpage=((page-1)/BLOCK*BLOCK)+1;
		int endpage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage)
			endpage=totalpage;
		
		vo.setTotalpage(totalpage);
		vo.setStartpage(startpage);
		vo.setEndpage(endpage);
		vo.setCurpage(page);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
}
