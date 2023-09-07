package com.sist.main;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.EmpDAO;
import com.sist.dao.EmpVO;
import com.sist.config.*;

@Component("mc")
public class MainClass {
	@Autowired
	private EmpDAO dao;
	public static void main(String[] args) {
		AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext(EmpConfig.class);
		MainClass mc=(MainClass)app.getBean("mc");
		List<EmpVO> list=mc.dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+"."+vo.getEname());
		}
		
		Scanner scan=new Scanner(System.in);
		System.out.print("번호 입력:");
		int empno=scan.nextInt();
		EmpVO vo=mc.dao.empDetailData(empno);
		System.out.println("사원 번호:"+vo.getEmpno());
		System.out.println("이름:"+vo.getEname());
		System.out.println("직위:"+vo.getJob());
		System.out.println("급여:"+vo.getSal());
		System.out.println("성과급:"+vo.getComm());
		System.out.println("부서번호:"+vo.getDeptno());
		System.out.println("입사일:"+vo.getHiredate());
		
	}
}
