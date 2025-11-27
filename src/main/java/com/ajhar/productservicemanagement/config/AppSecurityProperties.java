package com.ajhar.productservicemanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "app.security")
public class AppSecurityProperties {

    /**
     * List of admin emails configured in application.yml.
     */
    private List<String> admins;

    public List<String> getAdmins() {
        return admins;
    }

    public void setAdmins(List<String> admins) {
        this.admins = admins;
    }
}
