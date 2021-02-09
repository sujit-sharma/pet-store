package com.sujit.petservice.controller;

import com.sujit.petservice.model.PetEntity;
import com.sujit.petservice.repository.PetEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@Controller
@Component
@RequestMapping("/api/pet")
public class PetController {

    private final PetEntityRepository repository;

    @PostMapping
    public ResponseEntity<PetEntity> create(@RequestBody PetEntity entity) {
        log.info("Creating new pet {}", entity);
        return ResponseEntity.ok().build();
        //return ResponseEntity.ok(repository.save(entity));
    }
    @GetMapping
    public ResponseEntity<?> viewall() {
        log.info("view aLL METHOD called");
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<PetEntity> update(@RequestBody PetEntity entity, @PathVariable Long petId) {
        log.info("Updating Existing  pet {}", entity);
        return ResponseEntity.ok(repository.save(entity));
    }


}
