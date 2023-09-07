package com.sist.vo;

import lombok.Getter;
import lombok.Setter;
//1. 페이지 기법
//2. 쿠키
//3. 세션
//4. 로그인 => password 암호화 / 복호화
//5. Front => Vue => watch / computed / component / filter
//6. 회원가입 => 유효성 검사
@Getter@Setter
public class GoodsVO {
	private int no,discount,hit,account;
	private String name,sub,price,first_price,delivery,poster;
}
