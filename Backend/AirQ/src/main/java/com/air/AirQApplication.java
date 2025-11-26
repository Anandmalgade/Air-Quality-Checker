package com.air;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AirQApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirQApplication.class, args);
	}

}
