package com.sist.main2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
//		String[] xml= {"member.xml", "sawon.xml", "student.xml"};
		ApplicationContext app=new ClassPathXmlApplicationContext("app-*.xml");
		Sawon s=(Sawon)app.getBean("sa");
		s.init();
		s.print();
		Member m=(Member)app.getBean("mem");
		m.init();
		m.print();
		Student std=app.getBean("std",Student.class);
		std.init();
		std.print();
		
	}
}
