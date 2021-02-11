package com.sujit.petservice.repository;

import com.sujit.petservice.model.PetEntity;
import com.sujit.petservice.model.PetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PetEntityRepository extends JpaRepository<PetEntity, Long> {

    List<PetEntity> findAllByStatusIn(Set<PetStatus> petStatuses);

}
