package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.mongodb.io.ByteBufferOutputStream;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class DataBoardController {
	@Autowired
	private DataBoardDAO dao;
	
	//목록 출력
	@GetMapping("databoard/list.do")
	public String databoard_list(String page,Model model) {
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=rowSize*curpage - (rowSize-1);
		int end=rowSize*curpage;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<DataBoardVO> list=dao.databoardListData(map);
		int totalpage=dao.databoardTotalPage();
		
		model.addAttribute("list",list);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("curpage",curpage);
		model.addAttribute("main_jsp", "../databoard/list.jsp");
		return "main/main";
	}
	
	//데이터 추가
	@GetMapping("databoard/insert.do")
	public String databoard_insert(Model model) {
		
		model.addAttribute("main_jsp", "../databoard/insert.jsp");
		return "main/main";
	}
	@PostMapping("databoard/insert_ok.do")
	public String databoard_insert_ok(DataBoardVO vo) {
		List<MultipartFile> list=vo.getFiles();
		if(list==null) { //파일이 없는 상태(업로드가 안된 상태)
			vo.setFilename("");
			vo.setFilesize("");
			vo.setFilecount(0);
		} else { // 업로드가 된 상태
			String filenames="";
			String filesizes="";
			for(MultipartFile mf:list) {
				File file=new File("c:\\download\\"+mf.getOriginalFilename());
				try {
					mf.transferTo(file); // 파일 업로드
				} catch (Exception e) {}
				
				filenames += file.getName()+",";
				long len=file.length();
				filesizes+=len+",";
			}
			filenames=filenames.substring(0,filenames.lastIndexOf(","));
			filesizes=filesizes.substring(0,filesizes.lastIndexOf(","));
			vo.setFilename(filenames);
			vo.setFilesize(filesizes);
			vo.setFilecount(list.size());
		}
		
		dao.databoardInsert(vo);
		return "redirect:../databoard/list.do";
	}
	
	//요청 데이터가 없는 경우 : String으로 설정
	//모든 데이터는 String으로 받을 수 있다
	//매개변수는 순서가 없다, 여러개를 받을 수 있다
	/*
	 * 1) Model(Controller) => 매개변수 설정
	 *    매개변수 : 사용자 보내준 값을 받는다(1. 일반 데이터(int,String...)
	 *    							  2. 데이터를 모아서 처리
	 *    								=> VO(커맨드 객체)
	 *    							  3. checkbox => String[])
	 */
	@GetMapping("databoard/detail.do")
	public String databoard_detail(int no, Model model) {
		DataBoardVO vo=dao.databoardDetailData(no);
		
		if(vo.getFilecount()>0) {
			String filenames=vo.getFilename();
			StringTokenizer st=new StringTokenizer(filenames, ",");
			List<String> nList=new ArrayList<String>();
			while(st.hasMoreTokens()) {
				nList.add(st.nextToken());
			}
			String filesizes=vo.getFilesize();
			st=new StringTokenizer(filesizes, ",");
			List<String> sList=new ArrayList<String>();
			while(st.hasMoreTokens()) {
				sList.add(st.nextToken());
			}
			
			model.addAttribute("nList", nList);
			model.addAttribute("sList", sList);
		}
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../databoard/detail.jsp");
		return "main/main";
	}
	
	@GetMapping("databoard/download.do")
	public void databoardDownload(String fn, HttpServletResponse response) {
		try {
			File file=new File("c:\\download\\"+fn);
			response.setHeader("Content-Disposition", "attachement;filename="
						+ URLEncoder.encode(fn, "UTF-8"));
			//Download
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			//서버에서 파일 읽기
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
			//사용자에게 전송
			int i=0;//읽은 byte수
			byte[] buffer=new byte[1024];
			while((i=bis.read(buffer,0,1024))!=-1) {
				bos.write(buffer,0,i);
			}
			bis.close();
			bos.close();
		} catch (Exception e) {}
	}
	
	@PostMapping("databoard/find.do")
	public String databoard_find(String fs, String ss, Model model) {
		//DAO연결
		Map map=new HashMap();
		map.put("fs", fs);
		map.put("ss", ss);
		
		List<DataBoardVO> list=dao.databoardFindData(map);
		
		model.addAttribute("list", list);
		model.addAttribute("count", list.size());
		model.addAttribute("main_jsp", "../databoard/find.jsp");
		return "main/main";
	}
	
	@GetMapping("databoard/update.do")
	public String databoard_update(int no, Model model) {
		//DAO연동
		DataBoardVO vo=dao.databoardUpdateData(no);
		
		model.addAttribute("vo",vo);
		model.addAttribute("main_jsp", "../databoard/update.jsp");
		return "main/main";
	}
	
	@PostMapping("databoard/update_ok.do")
	@ResponseBody //승격 (@RestController)
	public String databoard_update_ok(DataBoardVO vo) {
		String result="";
		
		boolean bCheck=dao.databoardUpdate(vo);
		if(bCheck) {
			result="<script> "
				 + "location.href=\"../databoard/detail.do?no="+vo.getNo()+"\"; "
				 + "</script>";
		} else {
			result="<script>alert(\"비밀번호가 틀립니다\");history.back();</script>";
		}
		
		return result;
	}
	//삭제하는 창으로 이동
	//databoard/delete.do?no=${vo.no}
	@GetMapping("databoard/delete.do")
	public String databoard_delete(int no,Model model) {
		model.addAttribute("no",no);
		model.addAttribute("main_jsp", "../databoard/delete.jsp");
		return "main/main";
	}
	
	@PostMapping("databoard/delete_ok.do")
	@ResponseBody
	public String databoard_delete_ok(String pwd, int no) {
		String result="";
		
		boolean bCheck=dao.databoardDelete(no, pwd);
		if(bCheck) {
			result="<script> "
				 + "location.href=\"../databoard/list.do\""
				 + "</script>";
		} else {
			result="<script>alert(\"비밀번호가 틀립니다\");history.back();</script>";
		}
		
		return result;
	}
}
