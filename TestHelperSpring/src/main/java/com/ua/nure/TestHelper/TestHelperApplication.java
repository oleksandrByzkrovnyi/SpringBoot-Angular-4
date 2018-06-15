package com.ua.nure.TestHelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = "com.ua.nure.TestHelper")
@EnableJpaRepositories(basePackages = "com.ua.nure.TestHelper.repository")
@EntityScan(basePackages = "com.TestHelper.domain")
public class TestHelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestHelperApplication.class, args);
	}
}
