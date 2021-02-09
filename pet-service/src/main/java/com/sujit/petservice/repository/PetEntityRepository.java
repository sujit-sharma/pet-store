package com.sujit.petservice.repository;

import com.sujit.petservice.model.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetEntityRepository extends JpaRepository<PetEntity, Long> {

}
