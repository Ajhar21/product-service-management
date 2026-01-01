package com.ajhar.productservicemanagement.service;

import com.ajhar.productservicemanagement.config.AppSecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomOidcUserService extends OidcUserService {

    private final AppSecurityProperties adminProperties;

    public CustomOidcUserService(AppSecurityProperties adminProperties) {
        this.adminProperties = adminProperties;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) {
        OidcUser oidcUser = super.loadUser(userRequest);

        Set<GrantedAuthority> mappedAuthorities = new HashSet<>(oidcUser.getAuthorities());

        // Always assign ROLE_USER
        mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        // Assign ROLE_ADMIN if the email is in the admin list
        adminProperties.getAdmins().stream()
                .filter(email -> email.equalsIgnoreCase(oidcUser.getEmail()))
                .findFirst()
                .ifPresent(a -> mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")));

        // Assign ROLE_MODERATOR if the email is in the admin list
        adminProperties.getModerators().stream()
                .filter(email -> email.equalsIgnoreCase(oidcUser.getEmail()))
                .findFirst()
                .ifPresent(a -> mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_MODERATOR")));

        return new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
    }
}

