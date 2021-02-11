package com.sujit.petservice.validator;

import com.sujit.petservice.model.AppError;
import com.sujit.petservice.model.CategoryEntity;
import com.sujit.petservice.model.PetEntity;

import java.util.Set;

public interface PetValidator  {
        Set<AppError> validate(PetEntity petEntity);

}
