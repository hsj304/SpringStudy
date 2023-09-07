package com.sist.vo;
/*
NO	NUMBER
NAME	VARCHAR2(51 BYTE)
SUBJECT	VARCHAR2(1000 BYTE)
CONTENT	CLOB
PWD	VARCHAR2(10 BYTE)
REGDATE	DATE
HIT	NUMBER
GROUP_ID	NUMBER
GROUP_STEP	NUMBER
GROUP_TAB	NUMBER
ROOT	NUMBER
DEPTH	NUMBER
 */

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Getter@Setter
public class BoardVO {
	private int no,hit,group_id,group_step,group_tab,root,depth;
	private String name,subject,content,pwd,dbday;
	private Date regdate;
}
