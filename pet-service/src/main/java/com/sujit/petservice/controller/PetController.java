package com.sujit.petservice.controller;

import com.sujit.petservice.model.*;
import com.sujit.petservice.repository.CategoryRepository;
import com.sujit.petservice.repository.PetEntityRepository;
import com.sujit.petservice.repository.TagRepository;
import com.sujit.petservice.validator.CategoryValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pet")
public class PetController {

    private final PetEntityRepository repository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final CategoryValidator categoryValidator;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody PetEntity entity) {
        Set<AppError> errors = categoryValidator.validate(entity.getCategory());
        if( !errors.isEmpty()){
           return ResponseEntity.badRequest().body(errors);
        }
        log.info("Creating new pet {}", entity);
        entity.setCategory(categoryRepository.save(updateIfRequired(entity.getCategory())));
        Set<TagEntity> tagEntities = entity.getTags().stream().map(this::updateIfRequired).collect(Collectors.toSet());
        entity.setTags(new HashSet<>(tagRepository.saveAll(tagEntities)));
        log.info("Pet created successfully");
        return ResponseEntity.ok(repository.save(entity));
    }

    @GetMapping
    public ResponseEntity<?> viewall() {
        log.info("view aLL METHOD called");
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{petId}")
    public ResponseEntity<PetEntity> update(@RequestBody PetEntity updateEntity, @PathVariable Long petId) {
        log.info("Updating Existing  pet {}", updateEntity);
        Optional<PetEntity> exist = repository.findById(petId);
        if (exist.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PetEntity pet = exist.get();
        pet.setCategory(categoryRepository.save(updateIfRequired(updateEntity.getCategory())));
        pet.setName(updateEntity.getName());
        pet.setPhotoUrls(updateEntity.getPhotoUrls());
        Set<TagEntity> tagEntities = updateEntity.getTags().stream().map(this::updateIfRequired).collect(Collectors.toSet());
        pet.setTags(new HashSet<>(tagRepository.saveAll(tagEntities)));
        pet.setStatus(updateEntity.getStatus());
        log.info("Pet updated successfully");
        return ResponseEntity.ok(repository.save(pet));

    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetEntity> findById(@PathVariable Long petId) {
        log.info("Retrieving pet with given id");
        Optional<PetEntity> exist = repository.findById(petId);
        if(exist.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok( exist.get());

    }
    @GetMapping("/findByStatus")
    public ResponseEntity<List<PetEntity>> findByStatus(@RequestBody PetStatus[] status) {
        log.info("Finding pet by Id");
        List<PetEntity> petEntityList = new ArrayList<>();
        Arrays.stream(status).sequential().forEach(val -> petEntityList.addAll(repository.findByStatus(val).get()));
        log.info("Successfully retrieved pet by status");
        return ResponseEntity.ok(petEntityList);
    }

    @PostMapping("/{petId}")
    public ResponseEntity<PetEntity> updateformData(@RequestBody PetEntity petEntity){
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{petId}")
    public ResponseEntity deletePet(@PathVariable Long petId) {
        log.info("Deleting pet {} ", petId);
        Optional<PetEntity> exist = repository.findById(petId);
        if (exist.isEmpty()) {
            log.info("Pet id does not exist");
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(petId);
        log.info("Successfully Deleted ");
        return ResponseEntity.ok().build();
    }
    @PostMapping("/image{petId}/uploadImage")
    public ResponseEntity uploadImage(@PathVariable Long petId, @RequestParam("image")MultipartFile multipartFile) {
        log.info("Uploading pet image");
        Optional<PetEntity> exist = repository.findById(petId);
        if(exist.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PetEntity pet = exist.get();
        String imageName = StringUtils.cleanPath(multipartFile.getOriginalFilename()) + new Date().toString();
        Set<String> imagesUrls = pet.getPhotoUrls();
        imagesUrls.add(imageName);
        pet.setPhotoUrls(imagesUrls);
        String uploadDir  = "pet-image" + pet.getId();
        fileSave(uploadDir, imageName, multipartFile);
        log.info("File uploaded successfully");
        return ResponseEntity.ok().build();
    }

    private void fileSave(String uploadDir, String imageName, MultipartFile multipartFile) {
        log.info("Saving image");
        Path uploadPath = Paths.get(uploadDir);
        try {
            if(!Files.exists(uploadPath));{
                Files.createDirectories(uploadPath);
            }
            InputStream inputStream = multipartFile.getInputStream();
            Path filePath = uploadPath.resolve(imageName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            inputStream.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

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

}
