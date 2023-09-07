package com.sist.dao2;
/*
HAKBUN NOT NULL NUMBER       
NAME   NOT NULL VARCHAR2(51) 
KOR             NUMBER       
ENG             NUMBER       
MATH            NUMBER 
 */

import lombok.Setter;
import lombok.Getter;

@Getter@Setter
public class StudentVO {
	private int hakbun, kor, eng, math;
	private String name;
}
