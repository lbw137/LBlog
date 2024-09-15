package com.lyb.lyblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lyb.lyblog.mapper")
public class LyblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(LyblogApplication.class, args);
	}

}
