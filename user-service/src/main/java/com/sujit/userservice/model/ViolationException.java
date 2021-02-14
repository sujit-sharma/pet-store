package com.sujit.userservice.model;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ViolationException extends RuntimeException {

    private final Set<AppError> errors;


    public ViolationException(Exception ex, AppError error) {
        super(ex);
        this.errors = new HashSet<>();
        this.errors.add(error);
    }

    public ViolationException(AppError error) {
        this.errors = new HashSet<>();
        this.errors.add(error);
    }

    public ViolationException(Set<AppError> errors) {
        this.errors = errors;
    }

}
