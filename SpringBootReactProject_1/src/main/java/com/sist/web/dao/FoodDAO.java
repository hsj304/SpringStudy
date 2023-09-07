package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.entity.*;
import com.sist.web.entity.FoodEntity;

@Repository
public interface FoodDAO extends JpaRepository<FoodEntity, Integer> {
	public FoodEntity findByFno(int fno);
	
	@Query(value = "SELECT * FROM food_house WHERE cno=:cno", nativeQuery = true)
	public List<FoodEntity> foodListData(int cno);
}
