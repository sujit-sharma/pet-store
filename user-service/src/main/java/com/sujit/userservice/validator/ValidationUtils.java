package com.sujit.userservice.validator;

import com.sujit.userservice.model.AppError;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationUtils {
    public static <T> List<AppError> create(Set<ConstraintViolation<T>> violations) {
        return violations.stream().map(e -> new AppError(e.getPropertyPath().toString(),e.getMessage())).collect(Collectors.toList());
    }



}
