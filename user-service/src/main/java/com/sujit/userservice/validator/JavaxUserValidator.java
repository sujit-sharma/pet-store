package com.sujit.userservice.validator;

import com.sujit.userservice.model.AppError;
import com.sujit.userservice.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Validator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JavaxUserValidator implements UserValidator {
    private final Validator validator;
    @Override
    public List<AppError> validate(UserEntity userEntity) {
        return ValidationUtils.create(validator.validate(userEntity));
    }
}
