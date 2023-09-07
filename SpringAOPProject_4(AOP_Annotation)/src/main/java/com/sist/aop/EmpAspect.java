package com.sist.aop;
/*
 * 	aspect : AOP는 OOP를 대체하는 프로그램이 아니다
 * 			 OOP를 보완
 * 			 *** OOP => 콜백함수가 존재하지 않는다
 * 						------ 시스템에 의해서 자동 호출되는 함수 (main,service)
 * 				=> 한개, 두개의 공통으로 사용되는 내용 => 메소드로 처리
 * 					getConnection, disConnection
 * 				=> 여러개가 공통으로 사용되는 내용 => 클래스
 * 					CreateDataBase
 * 					-------------- 공통모듈
 * 	스프링에서는 반복적인 코딩을 모아서 관리 => 필요시에 의해 자동으로 호출
 * 	--------------------------------------------------- AOP
 * 	*** 사용자 정의는 많이 사용하지 않는다 :(라이브러리: 트랜잭션,보안,로그파일)
 * 
 * 	AOP
 * 	---
 *   어떤 메소드에 적용 : PointCut (메소드명을 지정)
 *   => pointcut ="execution(* com.sist.main.*.*(..))"
 *   						리턴형            클래스명 메소드명 (..)
 *   													   ---
 *   														매개변수(X),매개변수
 *   => pointcut="within("com.sist.main.*")"
 *   				패키지 단위로 등록
 *   호출되는 위치 설정 : JoinPoint
 *   	@Before
 *   	public String display()
 *   	{
 *   		@Before : getConnection()
 *   		try{
 *   			-------------------- @Around
 *   			== 핵심코딩
 *   			--------------------- @Around
 *   		}catch(Exception ex)
 *  		 {
 *  				@After-Throwing : 오류 처리 : rollback()
 *  		 }
 *  		finally
 *  		{
 *  			@After : setAutoCommit(true), disConnection()
 *  		}
 *  
 *  		return "" @After-Returning
 *  
 *   	}
 *   
 *   JoinPoint+Pointcut => Advice ==> Aspect
 *   Weaving : 통합 => Proxy패턴 (대리자)
 *   
 *   public class A 
 *   {
 *   	public void display()
 *   	{
 *   	}
 *   }
 *   
 *   public class Proxy
 *   {
 *   	private A a;
 *   	public Proxy(A a)
 *   	{
 *   		this.a=a;
 *   	}
 *  	 public void display()
 *  	 {
 *  		@Before => 설정된 메소드 호출
 *   		a.display();
 *   		@After => 설정된 메소드 호출
 *  	 }
 *   ]
 * 
 */
import java.util.*;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sist.dao.*;
@Aspect
@Component //메모리 할당
public class EmpAspect {
	@Autowired
	private EmpDAO dao;
	
	@Before("execution(* com.sist.dao.EmpDAO.emp*(..))")
	//try시작과 동시에 호출
	public void getConnection()
	{
		System.out.println("오라클 연결");
		dao.getConnection();
	}
	
	@After("execution(* com.sist.dao.EmpDAO.emp*(..))")
	public void disConnection()
	{
		System.out.println("오라클 해제");
		dao.disConnection();
	}
	
	//@AfterReturning("") //정상 수행 => return값을 받아서 처리
	@AfterReturning(value = "execution(* com.sist.dao.EmpDAO.emp*(..))", returning = "obj")
	public void print(Object obj) {
		System.out.println("After");
		List<EmpVO> list=(List<EmpVO>)obj;
		System.out.println("after2");
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno() + " " + vo.getEname() + " " + vo.getJob() + " " + vo.getDbday() + " " + vo.getSal());
		}
		System.out.println("after3");
	}
	//@AfterThrowing("") // catch를 수행했을 때 처리
	@AfterThrowing(value = "execution(* com.sist.dao.EmpDAO.emp*(..))", throwing = "ex")
	public void exception(Throwable ex) {
		System.out.println("에러발생...");
		ex.printStackTrace();
	}
}
