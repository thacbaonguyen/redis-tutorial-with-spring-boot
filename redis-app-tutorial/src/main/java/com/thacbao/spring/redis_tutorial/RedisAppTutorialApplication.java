package com.thacbao.spring.redis_tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RedisAppTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisAppTutorialApplication.class, args);
	}

}
