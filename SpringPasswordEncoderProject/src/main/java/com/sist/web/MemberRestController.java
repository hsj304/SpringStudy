package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.sist.dao.*;
// Security 5 ==> 반드시 BCryptPasswordEncoder 추가
@RestController
public class MemberRestController {
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private MemberDAO dao;
	
	@PostMapping(value = "member/join_ok.do", produces = "text/plain;charset=UTF-8")
	public String member_join(MemberVO vo) {
		System.out.println("id:"+vo.getId());
		System.out.println("pwd:"+vo.getPwd());
		System.out.println("name:"+vo.getName());
		System.out.println("sex:"+vo.getSex());
		String result="";
		try {
			String en=encoder.encode(vo.getPwd()); //암호화
			vo.setPwd(en);
			dao.memberInsert(vo);
			result="YES";
		} catch (Exception e) {
			result="NO";
			e.printStackTrace();
		}
		
		return result;
	}
	
	@PostMapping(value = "member/login_ok.do", produces = "text/plain;charset=UTF-8")
	public String member_login_ok(String id, String pwd, HttpSession session) {
		String result="";
		int count=dao.memberIdCheck(id);
		if(count==0) {
			result="NOID";
		} else {
			MemberVO vo=dao.memberLogin(id);
			if(encoder.matches(pwd, vo.getPwd())) {//실제값 / 암호화된 비밀번호 비교
				result="OK";
				//세션 저장
				session.setAttribute("id", id);
				session.setAttribute("name", vo.getName());
				session.setAttribute("sex", vo.getSex());
			} else {
				//비밀번호가 틀린 상태 ==> 같은 값인데 암호화는 다르다
				result="NOPWD";
			}
		}
		
		
		return result;
	}
}
