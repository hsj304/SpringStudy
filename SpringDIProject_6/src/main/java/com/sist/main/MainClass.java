package com.sist.main;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
import com.sist.vo.*;

public class MainClass {
	public static void main(String[] args) {
		
	}
	
	public void locationMain() {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		LocationDAO dao=(LocationDAO)app.getBean("ldao");
		List<LocationVO> list=dao.LocationListData();
		for(LocationVO vo:list) {
			System.out.println(vo.getNo()+"."+vo.getTitle());
		}
	}
	
	public void locationDetail() {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		LocationDAO dao=(LocationDAO)app.getBean("ldao");
		Scanner scan=new Scanner(System.in);
		System.out.print("명소 번호:");
		int no=scan.nextInt();
		LocationVO vo=dao.locationDetailData(no);
		System.out.println("Title:"+vo.getTitle());
		System.out.println("Address:"+vo.getAddress());
		System.out.println("Message:"+vo.getMsg());
	}

	public void shopMain() {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		ShopDAO dao=(ShopDAO)app.getBean("sdao");
		List<ShopVO> list=dao.shopListData();
		for(ShopVO vo:list) {
			System.out.println(vo.getNo()+"."+vo.getTitle());
		}
	}
	
	public void shopDetail() {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		ShopDAO dao=(ShopDAO)app.getBean("sdao");
		Scanner scan=new Scanner(System.in);
		System.out.print("번호 입력:");
		int no=scan.nextInt();
		ShopVO vo=dao.shopDetailData(no);
		System.out.println("Title:"+vo.getTitle());
		System.out.println("Address:"+vo.getAddress());
		System.out.println("Message:"+vo.getMsg());
	}

	public void natureMain() {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		NatureDAO dao=(NatureDAO)app.getBean("ndao");
		List<NatureVO> list=dao.natureListData();
		for(NatureVO vo:list) {
			System.out.println(vo.getNo()+"."+vo.getTitle());
		}
	}

	@Test
	public void natureDetail() {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		NatureDAO dao=(NatureDAO)app.getBean("ndao");
		System.out.print("번호 입력:");
		Scanner scan=new Scanner(System.in);
		int no=scan.nextInt();
		NatureVO vo=dao.natureDetailData(no);
		System.out.println("Title:"+vo.getTitle());
		System.out.println("Address:"+vo.getAddress());
		System.out.println("Message:"+vo.getMsg());
		
	}
}
