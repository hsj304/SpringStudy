package com.sist.vo;

import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class DataBoardVO {
	private int no,hit,filecount;
	private String name,subject,content,pwd,filename,filesize,dbday;
	private Date regdate;
	private List<MultipartFile> files; // 업로드된 파일 저장
}
