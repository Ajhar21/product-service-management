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
                                new Product("Royal Enfield", "Perfect cafe racer", BigDecimal.valueOf(400000.00)),
                                new Product("Samsung Galaxy S21", "High refresh rate display", BigDecimal.valueOf(90000.00)),
                                new Product("Dell XPS 13", "Compact ultrabook", BigDecimal.valueOf(120000.00)),
                                new Product("Canon EOS 1500D", "DSLR camera beginner friendly", BigDecimal.valueOf(55000.00)),
                                new Product("Nike Air Max", "Comfortable running shoes", BigDecimal.valueOf(12000.00)),
                                new Product("Adidas Ultraboost", "Premium running shoes", BigDecimal.valueOf(15000.00)),
                                new Product("Sony WH-1000XM4", "Noise-cancelling headphones", BigDecimal.valueOf(35000.00)),
                                new Product("Bose QuietComfort 45", "Top-tier headphones", BigDecimal.valueOf(40000.00)),
                                new Product("Apple Watch Series 7", "Fitness tracking smartwatch", BigDecimal.valueOf(45000.00)),
                                new Product("Fitbit Charge 5", "Health monitoring device", BigDecimal.valueOf(20000.00)),
                                new Product("GoPro Hero 10", "Action camera", BigDecimal.valueOf(50000.00)),
                                new Product("Logitech MX Master 3", "Ergonomic wireless mouse", BigDecimal.valueOf(12000.00)),
                                new Product("Razer DeathAdder V2", "Gaming mouse", BigDecimal.valueOf(8000.00)),
                                new Product("HP Pavilion 15", "Affordable laptop", BigDecimal.valueOf(80000.00)),
                                new Product("Lenovo ThinkPad X1", "Business ultrabook", BigDecimal.valueOf(150000.00)),
                                new Product("Samsung Galaxy Tab S8", "High-end tablet", BigDecimal.valueOf(70000.00)),
                                new Product("Apple iPad Air", "Lightweight tablet", BigDecimal.valueOf(65000.00)),
                                new Product("Microsoft Surface Pro 8", "2-in-1 laptop/tablet", BigDecimal.valueOf(130000.00)),
                                new Product("JBL Flip 6", "Portable Bluetooth speaker", BigDecimal.valueOf(10000.00)),
                                new Product("Marshall Stanmore II", "Premium speaker", BigDecimal.valueOf(30000.00)),
                                new Product("Canon Pixma MG2570S", "All-in-one printer", BigDecimal.valueOf(7000.00)),
                                new Product("Epson EcoTank L3150", "Ink tank printer", BigDecimal.valueOf(25000.00)),
                                new Product("Nintendo Switch", "Portable gaming console", BigDecimal.valueOf(35000.00)),
                                new Product("Sony PlayStation 5", "Next-gen console", BigDecimal.valueOf(80000.00)),
                                new Product("Xbox Series X", "Next-gen gaming console", BigDecimal.valueOf(75000.00)),
                                new Product("Logitech C920", "HD webcam", BigDecimal.valueOf(12000.00)),
                                new Product("Anker PowerCore 10000", "Portable charger", BigDecimal.valueOf(3500.00)),
                                new Product("Kindle Paperwhite", "E-reader with backlight", BigDecimal.valueOf(12000.00)),
                                new Product("Dyson V11 Vacuum", "Cordless vacuum cleaner", BigDecimal.valueOf(95000.00)),
                                new Product("Instant Pot Duo 7-in-1", "Multi-use pressure cooker", BigDecimal.valueOf(15000.00))
                        )
                );
            }
        };
    }
}



