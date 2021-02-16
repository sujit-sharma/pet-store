package com.sujit.userservice.validator;

import com.sujit.userservice.model.AppError;
import com.sujit.userservice.model.UserEntity;

import java.util.List;

public interface UserValidator {
    List<AppError> validate(UserEntity userEntity);
}
