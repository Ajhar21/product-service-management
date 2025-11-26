package com.ajhar.productservicemanagement.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MeController {

    @GetMapping("/api/me")
    public Map<String, Object> me(@AuthenticationPrincipal OidcUser oidcUser) {
        if (oidcUser == null) {
            return Map.of("error", "User not authenticated");
        }

        //http://localhost:8081/api/me

        String email = oidcUser.getEmail();
        String name = oidcUser.getFullName();

        System.out.println("Outpur Ajhar: "+oidcUser);


        return Map.of(
                "email", email,
                "name", name
        );
    }
}
