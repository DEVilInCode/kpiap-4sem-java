package com.javalabs.lab1TSR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Lab1TsrApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lab1TsrApplication.class, args);
	}

}
