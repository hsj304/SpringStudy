package com.sist.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyVO {
	private int no,fno;
	private String id,name,msg;
	private Date regdate;
}
