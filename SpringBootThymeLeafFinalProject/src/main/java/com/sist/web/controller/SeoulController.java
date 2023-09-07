package com.sist.web.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sist.web.dao.*;
import com.sist.web.entity.*;
import java.util.*;

@Controller
@RequestMapping("seoul/")
public class SeoulController {
	@Autowired
	private SeoulLocationDAO dao;
	
	@GetMapping("weather")
	public String seoul_weather(Model model) {
		String html="";
		  try
		  {
			  Document doc=Jsoup.connect("https://korean.visitseoul.net/weather").get();
			  Element section=doc.selectFirst("section#content");
			  html="<section id=\"content\">";
			  html+=section.html();
			  html+="</section>";
			  // <img src="https://korean.visitseoul.net/resources/theme/images/weather/img-weather10.png" alt="흐리고 비">
			  html=html.replace("src=\"","src=\"https://korean.visitseoul.net" );
			  html=html.replace("제공 : 케이웨더(Kweather)","" );
		  }catch(Exception ex) {}
		model.addAttribute("html", html);
		model.addAttribute("main_html", "seoul/weather");
		return "main";
	}
	
	@GetMapping("location")
	public String seoul_location(Model model, String page) {
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowsize=20;
		int start=rowsize*curpage-rowsize;
		List<SeoulEntity> list=dao.seoulLocationListData(start);
		
		int totalpage=dao.seoulLocationTotalPage();
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
		model.addAttribute("main_html", "seoul/seoul_location");
		return "main";
	}
	
	@GetMapping("nature")
	public String seoul_nature(Model model, String page) {
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowsize=20;
		int start=rowsize*curpage-rowsize;
		List<SeoulEntity> list=dao.seoulLocationListData(start);
		
		int totalpage=dao.seoulLocationTotalPage();
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
		model.addAttribute("main_html", "seoul/seoul_nature");
		return "main";
	}
	
	@GetMapping("shop")
	public String seoul_shop(Model model, String page) {
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowsize=20;
		int start=rowsize*curpage-rowsize;
		List<SeoulEntity> list=dao.seoulLocationListData(start);
		
		int totalpage=dao.seoulLocationTotalPage();
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
		model.addAttribute("main_html", "seoul/seoul_shop");
		return "main";
	}
}
