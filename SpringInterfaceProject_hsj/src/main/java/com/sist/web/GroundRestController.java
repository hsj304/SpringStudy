package com.sist.web;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.GroundDAO;
import com.sist.vo.Ground_DetailVO;

@RestController
public class GroundRestController {
	@Autowired
	private GroundDAO dao;
	
	@GetMapping(value = "main/list_vue.do", produces = "text/plain;charset=UTF-8")
	public String ground_list_vue(String page) {
		String result="";
		
		try {
			if(page==null) page="1";
			int curpage=Integer.parseInt(page);
			
			Map map=new HashMap();
			map.put("start", (curpage*12)-11);
			map.put("end", curpage*12);
			List<Ground_DetailVO> list=dao.groundListData(map);
			
			int totalpage=dao.groundTotalPage();
			JSONArray arr=new JSONArray();
			int i=0;
			for(Ground_DetailVO vo:list) {
				JSONObject obj=new JSONObject();
				obj.put("gno", vo.getGno());
				obj.put("gname", vo.getGname());
				obj.put("gprice", vo.getGprice());
				obj.put("gaddr", vo.getGaddr());
				obj.put("gnotice", vo.getGnotice());
				String image=vo.getGimage();
				if(image.contains("^")) {
					image=image.substring(0,image.indexOf("^"));
				}
				obj.put("gimage", image);
				if(i==0) {
					obj.put("curpage", curpage);
					obj.put("totalpage", totalpage);
				}
				arr.add(obj);
				i++;
			}
			result=arr.toJSONString();
		} catch (Exception e) {}
		
		return result;
	}
}
