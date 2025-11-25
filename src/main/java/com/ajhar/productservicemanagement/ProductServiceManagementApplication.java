package com.ajhar.productservicemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProductServiceManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceManagementApplication.class, args);
	}
}
