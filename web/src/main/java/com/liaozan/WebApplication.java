package com.liaozan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/14
 */
@SpringBootApplication
@EnableAsync
@EnableSwagger2
public class WebApplication {

	public static void main (String[] args) {
		try {
			SpringApplication.run(WebApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
