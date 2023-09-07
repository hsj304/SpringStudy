package com.sist.web;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.RecipeDAO;
import com.sist.vo.ChefVO;
import com.sist.vo.PageVO;
import com.sist.vo.RecipeVO;

@RestController
public class RecipeRestController {
	@Autowired
	private RecipeDAO dao;

	@GetMapping(value = "recipe/recipe_list_vue.do", produces = "text/plain;charset=UTF-8")
	public String recipe_list(int page) throws Exception {
		Map map = new HashMap();
		int rowsize = 20;
		int start = (rowsize * page) - (rowsize - 1);
		int end = rowsize * page;
		map.put("start", start);
		map.put("end", end);

		List<RecipeVO> list = dao.recipeListData(map);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(list);

		return json;
	}

	@GetMapping(value = "recipe/page_list_vue.do", produces = "text/plain;charset=UTF-8")
	public String page_list(int page) throws Exception {
		int count = dao.recipeRowCount();
		int totalpage = (int) (Math.ceil(count / 20.0));
		DecimalFormat df = new DecimalFormat("###,###,###");
		String strCount = df.format(count);

		final int BLOCK = 10;
		int start = ((page - 1) / BLOCK * BLOCK) + 1;
		int end = ((page - 1) / BLOCK * BLOCK) + BLOCK;
		PageVO vo = new PageVO();
		vo.setCount(strCount);
		vo.setStartpage(start);
		vo.setEndpage(end);
		vo.setCurpage(page);
		vo.setTotalpage(totalpage);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(vo);

		return json;
	}

	@GetMapping(value = "recipe/chef_list_vue.do", produces = "text/plain;charset=UTF-8")
	public String chef_list(int page) throws Exception {
		Map map = new HashMap();
		int rowsize = 20;
		int start = (rowsize * page) - (rowsize - 1);
		int end = rowsize * page;
		map.put("start", start);
		map.put("end", end);

		List<ChefVO> list = dao.chefListData(map);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(list);

		return json;

	}

	@GetMapping(value = "recipe/chef_page_vue.do", produces = "text/plain;charset=UTF-8")
	public String chef_page_list(int page) throws Exception {

		int totalpage = dao.chefTotalpage();

		final int BLOCK = 10;
		int start = ((page - 1) / BLOCK * BLOCK) + 1;
		int end = ((page - 1) / BLOCK * BLOCK) + BLOCK;
		PageVO vo = new PageVO();
		vo.setStartpage(start);
		vo.setEndpage(end);
		vo.setCurpage(page);
		vo.setTotalpage(totalpage);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(vo);

		return json;
	}

	@GetMapping(value = "recipe/chef_info_vue.do", produces = "text/plain;charset=UTF-8")
	public String chef_info(String chef) throws Exception {
		ChefVO vo = dao.chefInfoData(chef);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(vo);

		return json;
	}

	@PostMapping(value = "recipe/chef_find_vue.do", produces = "text/plain;charset=UTF-8")
	public String chef_find_vue(int page, String chef, String fd) throws Exception 
	{
		if(fd==null || fd.equals(""))
		{
			fd="all";
		}
		Map map = new HashMap();
		int rowsize = 20;
		int start = (rowsize * page) - (rowsize - 1);
		int end = rowsize * page;
		map.put("start", start);
		map.put("end", end);
		map.put("fd", fd);
		map.put("chef", chef);
		
		List<RecipeVO> list=dao.chefFindData(map);
		 
		ObjectMapper mapper=new ObjectMapper(); 
		String json=mapper.writeValueAsString(list);
		 
		return json;
	}
	
	//페이지
	@GetMapping(value = "recipe/page_info_vue.do",produces = "text/plain;charset=UTF-8")
	public String page_info(int page,String chef,String fd) throws Exception
	{ 
		if(fd==null || fd.equals(""))
		{
			fd="all";
		}
		Map map=new HashMap();
		map.put("chef", chef);
		map.put("fd", fd);
		int count = dao.chefFindCount(map);
		int totalpage=(int)(Math.ceil(count/20.0));
		
		final int BLOCK = 10;
		int start = ((page - 1) / BLOCK * BLOCK) + 1;
		int end = ((page - 1) / BLOCK * BLOCK) + BLOCK;
		
		if(end>totalpage)
			end=totalpage;
		PageVO vo = new PageVO();
		vo.setStartpage(start);
		vo.setEndpage(end);
		vo.setCurpage(page);
		vo.setTotalpage(totalpage);
		vo.setCount(String.valueOf(count));
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(vo);
		
		return json;
	}
	
}
