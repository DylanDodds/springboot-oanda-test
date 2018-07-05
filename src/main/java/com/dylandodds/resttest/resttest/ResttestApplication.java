package com.dylandodds.resttest.resttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
		"com.dylandodds.resttest.resttest.service",
		"com.dylandodds.resttest.resttest.engine",
		"com.dylandodds.resttest.resttest.config",
		"com.dylandodds.resttest.resttest.manager",
})
public class ResttestApplication {
	public static void main(String[] args) {
		SpringApplication.run(ResttestApplication.class, args);
	}
}
