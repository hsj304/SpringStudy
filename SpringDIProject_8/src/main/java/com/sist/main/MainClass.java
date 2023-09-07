package com.sist.main;
/*
 * 1. 프래임워크
 * 	  =라이브러리 : 완제품
 * 	  =프레임워크 : 레고
 * 		=> 스프링(전자정부 프레임워크(공기업), 애니프레임워크(대기업))
 * 		=> 개발에서 기본이 되는 틀을 제공(기능을 추가해서 사용)
 * 		=> 스프링의 목표(로드 존스) => 오픈 소스
 * 			1) 클래스와 클래스간의 느슨한 결합, 낮은 결합도 유지
 * 							  ----------------------
 * 								변경할때 추가될때 다른 클래스에 영향이 없이 개발
 * 								POFO(독립적인 클래스 제작)
 * 			2) 메소드마다 반복해서 사용하는 소스코드 => 공통 모듈로 분리(응집력이 높은 프로그램 제작)
 * 				=>AOP : OOP를 보완
 * 		=> 클래스를 모아서 관리(생성~소멸) => 컨테이너
 * 			생성에 필요한 데이터나 객체 주소가 필요할때도 있다 : DI
 * 
 * 		*** DI(객체 생성, 클래스의 연관 관계)
 * 		*** AOP : 공통 모듈(AOP 기반 => 로그파일, 트랜잭션, 보안)
 * 		*** DI/AOP => 인터페이스 기반
 * 		1. 컨테이너의 종류
 * 			BeanFactory : 가장 단순한 컨테이너
 * 							사용자가 만든 클래스만 관리 => DI
 * 							-------------------------CORE
 * 			ApplicationContext : AOP, 메세지 지원(JMS)
 * 								=> 구현된 클래스 
 * 									ClassPathApplicationContext
 * 									FileSystemXmlApplicationContext
 * 					|
 * 			WebApplicationContext : MVC(웹)
 * 			**Container 역할
 * 				1) DL => 클래스를 찾아주는 역할
 * 						getBean()
 * 				2) DI => 필요한 데이터를 받아서 초기화(변수)
 * 						= setter DI
 * 						= constructor DI(생성자의 매개변수를 이용)
 * 				=> 객체 생성
 * 				=> 객체 검색
 * 				=> 객체 소멸
 * 		2. DI(값을 스프링에 전송하는 방식)
 * 			주입(변수값을 주입, 객체 주소 설정)
 * 			***변수 => private : 변수에 접근이 어렵다
 * 			   ---------------------------------
 * 				= setter, 생성자
 * 					p:변수명(setXxx()) c:변수명(매개변수명)
 * 		***스프링에 지원하는 라이브러리
 * 			Spring Core : DI(객체 생성-소멸) => Container
 * 			Spring AOP : 공통모듈 => 자동 호출
 * 				= Before
 * 				= After
 * 				= After-Returning
 * 				= After-Throwing
 * 				= Around
 * 				= JoinPoint, PointCut, 위빙
 * 				= Advice
 * 			Spring ORM : 데이터베이스 연동(MyBatis, Hibernate, JPA)
 * 			Spring WEB : JSP연동
 * 			Spring MVC : View/Model/Controller
 * 			*** XML 사용법
 * 				스프링에서 객체를 생성하기 위해서는
 * 				= 클래스명(메모리 할당이 가능)을 전송
 * 				
 * 		3. AOP의 개념
 * 		4. MVC구조
 * 		5. 보안
 * 		***데이터베이스 연결(ORM:MyBatis, JPA)
 * 		------------------------스프링 입문
 */
public class MainClass {
	public static void main(String[] args) {
		String path="C:\\springDev\\springStudy\\SpringDIProject_8\\src\\main\\java\\com\\sist\\main\\app.xml";
		ApplicationContext app=new ClassPathApplicationContext(path);
		
		Sawon sa=(Sawon)app.getBean("sa");
		System.out.println("사번:"+sa.getSabun());
		System.out.println("이름:"+sa.getName());
		System.out.println("부서:"+sa.getDept());
		System.out.println("직위:"+sa.getJob());
	}
}
