package com.ajhar.productservicemanagement.controller;

import com.ajhar.productservicemanagement.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
public class ProductControllerIntegrationTest {

    @Autowired
    private ProductController productController;

    private MockMvc mockMvc;

    // Sample product data
    private Product lenovoThinkPad;
    private Product samsungGalaxyTab;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        // Initialize sample product data
        lenovoThinkPad = new Product("Lenovo ThinkPad X1", "Business ultrabook", BigDecimal.valueOf(150000.00));
        samsungGalaxyTab = new Product("Samsung Galaxy Tab S8", "High-end tablet", BigDecimal.valueOf(70000.00));
    }

    // Test that non-admin users get 403 when trying to POST
    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testCreateProductForbiddenForNonAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .with(csrf())
                        .contentType("application/json")
                        .content("{ \"name\": \"Lenovo ThinkPad X1\", \"description\": \"Business ultrabook\", \"price\": 150000.00 }"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    // Test that non-admin users get 403 when trying to DELETE
    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testDeleteProductForbiddenForNonAdmin() throws Exception {
        Long productId = 1L; // Use an existing product ID for your test

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/" + productId)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
/*
    // Test that non-admin users get 403 when trying to PUT
    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void testUpdateProductForbiddenForNonAdmin() throws Exception {
        Long productId = 1L; // Use an existing product ID for your test

        mockMvc.perform(MockMvcRequestBuilders.put("/api/products/" + productId)
                        .with(csrf())
                        .contentType("application/json")
                        .content("{ \"name\": \"Updated Lenovo ThinkPad\", \"description\": \"Updated description\", \"price\": 160000.00 }"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

 */

}
