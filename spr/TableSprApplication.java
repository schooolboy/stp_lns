package com.nikita.dem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.nikita.dem"})
public class TableSprApplication {

	public static void main(String[] args) {
		SpringApplication.run(TableSprApplication.class, args);
	}
}
