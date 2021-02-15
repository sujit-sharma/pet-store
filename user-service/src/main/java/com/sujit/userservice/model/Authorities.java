package com.sujit.userservice.model;

import java.util.*;
import java.util.stream.Collectors;

public enum Authorities {

    ROLE_ADMIN,
    ROLE_PET_USER,
    ROLE_STORE_USER,
    ROLE_ORDER_USER;

    public static void validate(Set<String> authorities) {
        Set<String> allowedAuthorities = Arrays.stream(Authorities.values()).map(Enum::name).collect(Collectors.toSet());
        Optional.ofNullable(authorities)
                .orElseGet(HashSet::new)
                .forEach(s -> {
                    if (!allowedAuthorities.contains(s)) {
                        throw new ViolationException(new AppError("authorities", "Invalid authorities"));
                    }
                });
    }
}
