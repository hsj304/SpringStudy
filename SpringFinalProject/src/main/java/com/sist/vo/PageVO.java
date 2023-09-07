package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageVO {
	private int curpage;
	private int totalpage;
	private int startpage,endpage;
	private String count;
}
