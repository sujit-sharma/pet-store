package com.sujit.petservice.controller;

import com.sujit.petservice.model.PetEntity;
import com.sujit.petservice.repository.PetEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController("/api/pet")
public class PetController {

    private final PetEntityRepository repository;

    @PostMapping
    public ResponseEntity<PetEntity> create(@RequestBody PetEntity entity) {
        log.info("Creating new pet {}", entity);
        return ResponseEntity.ok(repository.save(entity));
    }

    @PutMapping
    public ResponseEntity<PetEntity> update(@RequestBody PetEntity entity, @PathVariable Long petId) {
        log.info("Updating Existing  pet {}", entity);
        return ResponseEntity.ok(repository.save(entity));
    }


}
