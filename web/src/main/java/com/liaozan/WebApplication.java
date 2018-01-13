package com.liaozan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/14
 */
@SpringBootApplication
@EnableAsync
@MapperScan(value = "com.liaozan.biz.mapper")
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
