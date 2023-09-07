package com.sist.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.MemberVO;

public interface MemberMapper {
	@Insert("INSERT INTO springTestMember(id,pwd,name,sex) VALUES("
		  + "#{id},#{pwd},#{name},#{sex})")
	public void memberInsert(MemberVO vo);
	
	@Select("SELECT COUNT(*) from springTestMember WHERE id=#{id}")
	public int memberIdCheck(String id);
	
	@Select("SELECT pwd,name,sex FROM springTestMember WHERE id=#{id}")
	public MemberVO memberLogin(String id);
}
