package com.sujit.petservice.validator;

import com.sujit.petservice.model.AppError;
import com.sujit.petservice.model.TagEntity;

import java.util.Set;

public interface TagValidator {

    Set<AppError> validate(TagEntity tagEntity);
}
