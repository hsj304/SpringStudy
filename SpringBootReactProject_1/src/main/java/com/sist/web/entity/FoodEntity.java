package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "food_house")
@Getter
@Setter
public class FoodEntity {
	@Id
	private int fno;
	private int hit;
	private double score;
	private String name,address,phone,type,price,time,parking,menu,poster;
}
