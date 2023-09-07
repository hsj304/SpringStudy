package com.sist.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import java.util.*;
import com.sist.dao.*;

@Component
public class MainClass {
	@Autowired
	private FoodService service;
	
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		Scanner scan=new Scanner(System.in);
		
		MainClass mc=(MainClass)app.getBean("mainClass");
		List<CategoryVO> list=mc.service.categoryListData();
		for(CategoryVO vo:list) {
			System.out.println(vo.getCno()+"."+vo.getTitle());
		}
		System.out.println("=============================");
		System.out.print("카테고리 번호 선택:");
		int cno=scan.nextInt();
		List<FoodVO> flist=mc.service.foodCategoryListData(cno);
		for(FoodVO vo:flist) {
			System.out.println(vo.getFno()+"."+vo.getName());
		}
		System.out.println("=============================");
		System.out.print("맛집 번호 선택:");
		int fno=scan.nextInt();
		FoodVO vo=mc.service.foodDetailData(fno);
		System.out.println("맛집명:"+vo.getName()+"("+vo.getScore()+")");
		System.out.println("주소:"+vo.getAddress());
		System.out.println("전화:"+vo.getPhone());
		System.out.println("음식종류:"+vo.getType());
		System.out.println("가격대:"+vo.getPrice());
		System.out.println("주차:"+vo.getParking());
		System.out.println("영업시간:"+vo.getTime());
		System.out.println("메뉴:"+vo.getMenu());
	}
}
