package com.sist.vo;

import lombok.Setter;

import lombok.Getter;

@Getter@Setter
public class FoodVO {
	private int fno,cno;
	private String name,phone,type,parking,price,time,menu,poster,address;
	private Double score;
	private String sessionId;
}
