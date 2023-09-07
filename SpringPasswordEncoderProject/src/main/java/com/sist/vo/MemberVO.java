package com.sist.vo;
import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class MemberVO {
	private String id,pwd,sex,name,msg;
	private Date regdate;
}
