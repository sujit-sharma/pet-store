package com.sujit.petservice.service;

import com.sujit.petservice.model.*;
import com.sujit.petservice.repository.CategoryRepository;
import com.sujit.petservice.repository.PetEntityRepository;
import com.sujit.petservice.repository.TagRepository;
import com.sujit.petservice.validator.CategoryValidator;
import com.sujit.petservice.validator.PetValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetService {
    private final PetEntityRepository repository;
    private final PetValidator petValidator;
    private final CategoryValidator categoryValidator;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    public PetEntity create(PetEntity entity) {
        Set<AppError> errors = petValidator.validate(entity);
        if(errors == null) errors = new HashSet<>();
        errors.addAll(categoryValidator.validate(entity.getCategory()));
        AppError.raiseAllIfNecessary(errors);

        log.info("Creating new pet {}", entity);
        entity.setCategory(categoryRepository.save(updateIfRequired(entity.getCategory())));
        Set<TagEntity> tagEntities = entity.getTags().stream().map(this::updateIfRequired).collect(Collectors.toSet());
        entity.setTags(new HashSet<>(tagRepository.saveAll(tagEntities)));
        log.info("Pet created successfully");
        return repository.save(entity);

    }

    private CategoryEntity updateIfRequired(CategoryEntity request) {
        String name = StringUtils.capitalize(request.getName());
        return categoryRepository.findByName(name)
                .map(entity -> {
                    entity.setName(name);
                    return entity;
                }).orElseGet(() -> {
                    request.setName(name);
                    return request;
                });
    }

    private TagEntity updateIfRequired(TagEntity request) {
        String name = StringUtils.capitalize(request.getName());
        return tagRepository.findByName(name)
                .map(entity -> {
                    entity.setName(name);
                    return entity;
                }).orElseGet(() -> {
                    request.setName(name);
                    return request;
                });
    }

    public PetEntity update(Long petId, PetEntity updateEntity) {
        PetEntity pet = repository.findById(petId).orElseThrow(EntityNotFound::new);
        pet.setCategory(categoryRepository.save(updateIfRequired(updateEntity.getCategory())));
        pet.setName(updateEntity.getName());
        pet.setPhotoUrls(updateEntity.getPhotoUrls());
        Set<TagEntity> tagEntities = updateEntity.getTags().stream().map(this::updateIfRequired).collect(Collectors.toSet());
        pet.setTags(new HashSet<>(tagRepository.saveAll(tagEntities)));
        pet.setStatus(updateEntity.getStatus());
        log.info("Pet updated successfully");
        return null;
    }

    public void deleteById(Long petId) {
        PetEntity entity = repository.findById(petId).orElseThrow(EntityNotFound::new);
        repository.delete(entity);
        log.info("Successfully Deleted ");
    }

    public List<PetEntity> viewAll() {
        return repository.findAll();
    }


}