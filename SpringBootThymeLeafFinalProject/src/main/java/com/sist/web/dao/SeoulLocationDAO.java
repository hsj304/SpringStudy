package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;
import com.sist.web.entity.*;

public interface SeoulLocationDAO extends JpaRepository<SeoulEntity, Integer>{
	@Query(value = "SELECT * FROM seoul_location "
				 + "ORDER BY no ASC LIMIT :start,20", nativeQuery = true)
	public List<SeoulEntity> seoulLocationListData(int start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/20.0) FROM seoul_location", nativeQuery = true)
	public int seoulLocationTotalPage();
}
