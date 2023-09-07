package com.sist.main2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app2.xml");
		StudentSystem ss=(StudentSystem)app.getBean("ss");
		ss.print();
	}
}
