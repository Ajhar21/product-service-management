package com.ajhar.productservicemanagement.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

//http://localhost:8081/swagger-ui.html

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Product Service Management API",
                description = "REST API for managing products with RBAC, pagination, sorting, and OAuth2 (Google) authentication.",
                version = "1.0.0",
                contact = @Contact(
                        name = "MD AJHARUL ISLAM AKANDA",
                        email = "2017ajharakanda@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        )
)
public class OpenApiConfig {
}
