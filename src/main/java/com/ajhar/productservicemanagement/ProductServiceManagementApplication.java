package com.ajhar.productservicemanagement;

import com.ajhar.productservicemanagement.config.AppSecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(AppSecurityProperties.class)
public class ProductServiceManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceManagementApplication.class, args);
	}
}
