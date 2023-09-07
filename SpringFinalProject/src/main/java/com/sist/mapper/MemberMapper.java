package com.sist.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.MemberVO;

public interface MemberMapper {
   @Select("SELECT COUNT(*) FROM springMember "
		  +"WHERE id=#{id}")
   public int memberIdCheck(String id);
   @Select("SELECT COUNT(*) FROM springMember "
		  +"WHERE email=#{email}")
   public int memberEmailCheck(String email);

   @Insert("INSERT INTO springMember VALUES("
		  +"#{id},#{pwd},#{name},#{sex},#{birthday},#{email},"
		  +"#{post},#{addr1},#{addr2},#{phone},"
		  +"#{content},'n',SYSDATE,'ROLE_USER')")
   public void memberInsert(MemberVO vo);
   
   @Select("SELECT pwd,name,role FROM springMember WHERE id=#{id}")
   public MemberVO memberInfoData(String id);
}