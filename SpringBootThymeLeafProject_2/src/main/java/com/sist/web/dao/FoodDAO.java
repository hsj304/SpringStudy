package com.sist.web.dao;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;

@Repository
public interface FoodDAO extends JpaRepository<FoodEntity, Integer>{
//	public FoodEntity findByFno(int fno);
	//SELECT * FROM food_location WHERE fno=#{fno}
	
	@Query(value = "SELECT * FROM food_location "
				 + "WHERE address LIKE CONCAT('%',:fd,'%') "
				 + "ORDER BY fno ASC "
				 + "LIMIT :start, 12", nativeQuery = true)
	public List<FoodEntity> foodFindData(@Param("fd") String fd, @Param("start") Integer start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/12.0) FROM food_location "
			+ "		WHERE address LIKE CONCAT('%',:fd,'%')", nativeQuery = true)
	public int foodFindTotalPage(String fd);
	
	public FoodEntity findByFno(@Param("fno") Integer fno);
	//SELECT * FROM food_location WHERE fno=:fno
}
