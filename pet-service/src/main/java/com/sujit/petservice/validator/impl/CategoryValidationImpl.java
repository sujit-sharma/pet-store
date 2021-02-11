package com.sujit.petservice.validator.impl;

import com.sujit.petservice.model.AppError;
import com.sujit.petservice.model.CategoryEntity;
import com.sujit.petservice.validator.CategoryValidator;
import com.sujit.petservice.validator.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import javax.validation.Validator;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CategoryValidationImpl implements CategoryValidator {
    private final Validator validator;
    @Override
    public Set<AppError> validate(CategoryEntity category) {
        return ValidationUtil.create(validator.validate(category));
    }
}
