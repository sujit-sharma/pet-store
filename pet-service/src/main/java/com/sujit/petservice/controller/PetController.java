package com.sujit.petservice.controller;

import com.sujit.petservice.model.*;
import com.sujit.petservice.repository.PetEntityRepository;
import com.sujit.petservice.service.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    private final PetService petService;

    @Value("${upload.dir}")
    private String uploadDir;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody PetEntity entity) {
        return ResponseEntity.ok(petService.create(entity));
    }

    @GetMapping
    public ResponseEntity<?> viewall() {
        log.info("view aLL method called");
        return ResponseEntity.ok(petService.viewAll());
    }

    @PutMapping("/{petId}")
    public ResponseEntity<PetEntity> update(@RequestBody PetEntity updateEntity, @PathVariable Long petId) {
        log.info("Updating Existing  pet {}", updateEntity);
        return ResponseEntity.ok(petService.update(petId, updateEntity));
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetEntity> findById(@PathVariable Long petId) {
        log.info("Retrieving pet with given id");
        return ResponseEntity.ok(repository.findById(petId).orElseThrow(EntityNotFound::new));

    }

    @GetMapping("/findByStatus")
    public ResponseEntity<List<PetEntity>> findByStatus(@RequestParam("statuses") String statuses) {
        log.info("Finding pet by Id");
        Set<PetStatus> petStatuses = Optional.ofNullable(statuses)
                .map(s -> Arrays.stream(s.split(",")).map(PetStatus::valueOf).collect(Collectors.toSet()))
                .orElseGet(HashSet::new);
        List<PetEntity> all = repository.findAllByStatusIn(petStatuses);
        log.info("Successfully retrieved pet by status");
        return ResponseEntity.ok(all);
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Object> deletePet(@PathVariable Long petId) {
        log.info("Deleting pet {} ", petId);
        petService.deleteById(petId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{petId}/uploadImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadImage(@PathVariable Long petId, @RequestPart("image") MultipartFile multipartFile) {
        log.info("Uploading pet image");
        Optional<PetEntity> exist = repository.findById(petId);
        if (exist.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PetEntity pet = exist.get();
        Set<String> urls = Optional.ofNullable(pet.getPhotoUrls()).orElseGet(HashSet::new);
        String fileUrl = fileSave(pet.getId(), multipartFile);
        urls.add(fileUrl);
        pet.setPhotoUrls(urls);
        log.info("File uploaded successfully");
        return ResponseEntity.ok().build();
    }

    private String fileSave(Long petId, MultipartFile multipartFile) {
        log.info("Saving image");
        String imageName = petId + "-" + StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        String dir = this.uploadDir + File.separator + "pet-image";
        Path uploadPath = Paths.get(dir);
        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            InputStream inputStream = multipartFile.getInputStream();
            Path filePath = uploadPath.resolve(imageName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            inputStream.close();
            return dir + File.separator + imageName;
        } catch (IOException ioException) {
            throw new ViolationException(ioException, new AppError("image", "Error while saving image"));
        }
    }


}
