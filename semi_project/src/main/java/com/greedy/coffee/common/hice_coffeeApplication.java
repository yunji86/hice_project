package com.greedy.coffee.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.greedy.coffee")
public class hice_coffeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(hice_coffeeApplication.class, args);
	}

}
