package com.abdirahman.kmbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.abdirahman.kmbank.model")
public class KmbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(KmbankApplication.class, args);
	}

}
