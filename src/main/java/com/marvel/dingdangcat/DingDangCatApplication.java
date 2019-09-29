package com.marvel.dingdangcat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.marvel.dingdangcat.mapper")
public class DingDangCatApplication {

	public static void main(String[] args) {
		SpringApplication.run(DingDangCatApplication.class, args);
	}
}
