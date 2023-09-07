package com.sist.main3;

public class MainClass {

	public static void main(String[] args) {
		ApplicationContext app= new ApplicationContext();
		A a=(A)app.getBean("a");
		a.display();
		
		B b=(B)app.getBean("b");
		b.display();
	}

}
