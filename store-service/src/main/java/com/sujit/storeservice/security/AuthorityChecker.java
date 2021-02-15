package com.sujit.storeservice.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AuthorityChecker {

    public boolean hasAdminOrStoreUser() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authorities.contains("ROLE_ADMIN") && authorities.contains("ROLE_STORE_USER");
    }

}
