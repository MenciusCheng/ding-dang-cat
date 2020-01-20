package com.marvel.dingdangcat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(value = "com.marvel.dingdangcat.mapper")
@EnableScheduling
public class DingDangCatApplication {

	private static ConfigurableApplicationContext applicationContext;
	public static ConfigurableApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(DingDangCatApplication.class, args);
	}
}
