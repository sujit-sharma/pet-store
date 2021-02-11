package com.sujit.petservice.validator;

import com.sujit.petservice.model.AppError;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationUtil {

    public static <T> Set<AppError> create(Set<ConstraintViolation<T>> violations) {
        return violations.stream().map(e -> new AppError(e.getPropertyPath().toString(),e.getMessage())).collect(Collectors.toSet());
    }
}
