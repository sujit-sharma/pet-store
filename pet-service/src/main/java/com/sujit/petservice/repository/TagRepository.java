package com.sujit.petservice.repository;

import com.sujit.petservice.model.CategoryEntity;
import com.sujit.petservice.model.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
    Optional<TagEntity> findByName(String name);

}
