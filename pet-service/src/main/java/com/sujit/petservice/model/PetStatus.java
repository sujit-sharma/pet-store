package com.sujit.petservice.model;

import lombok.Getter;

@Getter
public enum PetStatus {
    AVAILABLE("AVAILABLE"),
    PENDING("PENDING"),
    SOLD("SOLD");
    private String status;

    PetStatus(String status){
        this.status = status;
    }

}
