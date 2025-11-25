package com.ajhar.productservicemanagement;

import com.ajhar.productservicemanagement.entity.Product;
import com.ajhar.productservicemanagement.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.saveAll(
                        List.of(
                                new Product("Iphone 10", "Super emulated display", BigDecimal.valueOf(100000.00)),
                                new Product("Macbook 5", "Small display", BigDecimal.valueOf(1000050.00)),
                                new Product("Cricket Bat", "With C shape", BigDecimal.valueOf(10000.00)),
                                new Product("Badminton Bat", "High pressure tolerable", BigDecimal.valueOf(15000.00)),
                                new Product("Royal Enfield", "Perfect cafe racer", BigDecimal.valueOf(400000.00))
                        )
                );
            }
        };
    }
}
