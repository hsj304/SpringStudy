package com.sist.main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;
@Component
public class MainClass {
	@Autowired
	private EmpDAO dao;
	
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		MainClass mc=(MainClass)app.getBean("mainClass");
		mc.dao.empListData();
	}

}
