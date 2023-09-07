package com.sist.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.sist.web.controller",
		"com.sist.web.dao",
		"com.sist.web.service"
})
public class SpringBootThymeLeafProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootThymeLeafProjectApplication.class, args);
	}

}
