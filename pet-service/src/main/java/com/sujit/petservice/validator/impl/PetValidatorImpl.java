package com.sujit.petservice.validator.impl;

import com.sujit.petservice.model.AppError;
import com.sujit.petservice.model.PetEntity;
import com.sujit.petservice.validator.PetValidator;
import com.sujit.petservice.validator.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Validator;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PetValidatorImpl implements PetValidator {
    private final Validator validator;
    @Override
    public Set<AppError> validate(PetEntity petEntity) {
        return ValidationUtil.create(validator.validate(petEntity));
    }
}
