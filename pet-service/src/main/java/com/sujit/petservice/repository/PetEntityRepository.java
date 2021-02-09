package com.sujit.petservice.repository;

import com.sujit.petservice.model.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetEntityRepository extends JpaRepository<PetEntity, Long> {

}
