package com.sujit.petservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppError {
    private String field;
    private String message;


    public static <T> Set<AppError> create(Set<ConstraintViolation<T>> violations) {
        return violations.stream().map(e -> new AppError(e.getPropertyPath().toString(),e.getMessage())).collect(Collectors.toSet());
    }
}
