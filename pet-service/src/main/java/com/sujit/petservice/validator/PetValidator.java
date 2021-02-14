package com.sujit.petservice.validator;

import com.sujit.petservice.model.AppError;
import com.sujit.petservice.model.PetEntity;
import org.springframework.stereotype.Component;

import java.util.Set;

public interface PetValidator  {
        Set<AppError> validate(PetEntity petEntity);

}
