package com.sujit.petservice.controller;

import com.sujit.petservice.model.CategoryEntity;
import com.sujit.petservice.model.PetEntity;
import com.sujit.petservice.model.TagEntity;
import com.sujit.petservice.repository.CategoryRepository;
import com.sujit.petservice.repository.PetEntityRepository;
import com.sujit.petservice.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController
@Controller
@Component
@RequestMapping("/api/pet")
public class PetController {

    private final PetEntityRepository repository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    @PostMapping
    public ResponseEntity<PetEntity> create(@RequestBody PetEntity entity) {
        log.info("Creating new pet {}", entity);
        CategoryEntity category = entity.getCategory();
        category.setName(StringUtils.capitalize(category.getName()));
        CategoryEntity categoryEntity = categoryRepository.findByName(category.getName()).orElseGet(() -> categoryRepository.save(category));

        Set<TagEntity> tagSet = entity.getTags();

        tagSet.stream().forEach(tag -> {tag.setName(StringUtils.capitalize(tag.getName()));
            tagRepository.findByName(tag.getName()).orElseGet(() -> tagRepository.save(tag));


        });
        entity.setCategory(categoryEntity);
        return ResponseEntity.ok(repository.save(entity));
    }
    @GetMapping
    public ResponseEntity<?> viewall() {
        log.info("view aLL METHOD called");
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping
    public ResponseEntity<PetEntity> update(@RequestBody PetEntity entity, @PathVariable Long petId) {
        log.info("Updating Existing  pet {}", entity);
        return ResponseEntity.ok(repository.save(entity));
    }


}
