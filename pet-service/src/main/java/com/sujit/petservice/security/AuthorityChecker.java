package com.sujit.petservice.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AuthorityChecker {

    public boolean hasAdminOrPetUser() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authorities.contains("ROLE_ADMIN") && authorities.contains("ROLE_PET_USER");
    }

}
