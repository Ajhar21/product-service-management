package com.ajhar.productservicemanagement.controller;

import com.ajhar.productservicemanagement.config.AppSecurityProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {

    private final AppSecurityProperties appSecurityProperties;

    public AdminController(AppSecurityProperties appSecurityProperties) {
        this.appSecurityProperties = appSecurityProperties;
    }

    //http://localhost:8081/api/admins
    @GetMapping("/admins")
    @PreAuthorize("hasRole('ADMIN')")
    public List<String> getAdmins() {
        return appSecurityProperties.getAdmins();
    }
}
