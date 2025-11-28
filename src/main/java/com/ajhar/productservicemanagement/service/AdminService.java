package com.ajhar.productservicemanagement.service;

import com.ajhar.productservicemanagement.config.AppSecurityProperties;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AppSecurityProperties appSecurityProperties;

    public AdminService(AppSecurityProperties appSecurityProperties) {
        this.appSecurityProperties = appSecurityProperties;
    }

    public boolean isAdmin(String email) {
        if (email == null) {
            return false;
        }
        return appSecurityProperties.getAdmins() != null
                && appSecurityProperties.getAdmins().contains(email.toLowerCase());
    }
}
