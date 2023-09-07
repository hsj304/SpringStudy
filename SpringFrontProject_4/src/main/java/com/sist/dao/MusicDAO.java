package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.MusicMapper;
import com.sist.vo.MusicVO;

@Repository
public class MusicDAO {
	@Autowired
	private MusicMapper mapper;
	
//	@Select("SELECT * FROM genie_music "
//		  + "WHERE cno=#{cno}")
	public List<MusicVO> musicListData(int cno){
		return mapper.musicListData(cno);
	}
	
//	@Select("SELECT * FROM genie_music "
//		  + "WHERE mno=#{mno}")
	public MusicVO musicDetailData(int mno) {
		return mapper.musicDetailData(mno);
	}
	
}
