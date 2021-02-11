package com.sujit.petservice.validator.impl;

import com.sujit.petservice.model.AppError;
import com.sujit.petservice.model.TagEntity;
import com.sujit.petservice.validator.TagValidator;
import com.sujit.petservice.validator.ValidationUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.Validator;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class TagValidatorImpl implements TagValidator {
    private final Validator validator;
    @Override
    public Set<AppError> validate(TagEntity tagEntity) {
        return ValidationUtil.create(validator.validate(tagEntity));
    }
}
