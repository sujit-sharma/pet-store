package com.sujit.petservice.validator;

import com.sujit.petservice.model.AppError;
import com.sujit.petservice.model.CategoryEntity;

import java.util.Set;

public interface CategoryValidator {

    Set<AppError> validate(CategoryEntity category);

}
