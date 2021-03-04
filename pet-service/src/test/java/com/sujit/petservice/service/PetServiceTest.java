package com.sujit.petservice.service;

import com.sujit.petservice.model.*;
import com.sujit.petservice.repository.CategoryRepository;
import com.sujit.petservice.repository.PetEntityRepository;
import com.sujit.petservice.repository.TagRepository;
import com.sujit.petservice.validator.CategoryValidator;
import com.sujit.petservice.validator.PetValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetEntityRepository repository;
    @Mock
    private PetValidator petValidator;
    @Mock
    private CategoryValidator categoryValidator;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private PetService petService;

    @Test
    void whenPetValidatorRaiseErrorsThenShouldThrowViolations() {

        System.out.println(petService + " " +petValidator +" " + repository);
        when(petValidator.validate(any(PetEntity.class))).thenReturn(of(new AppError()));

        Assertions.assertThrows(ViolationException.class, () -> {
            petService.create(new PetEntity());
        });

    }

    private Set<AppError> of(AppError... errors) {
        return Stream.of(errors).collect(Collectors.toSet());
    }

    @Test
    void whenPetValidatorDoesNotRaiseErrorsThenShouldNotThrowAnyExceptions() {

        when(petValidator.validate(any(PetEntity.class))).thenReturn(new HashSet<>());
        when(categoryValidator.validate(any(CategoryEntity.class))).thenReturn(new HashSet<>());
        ArgumentCaptor<PetEntity> petArgumentCaptor  = ArgumentCaptor.forClass(PetEntity.class);

        PetEntity mockedPetEntity = new PetEntity();
        CategoryEntity category = new CategoryEntity();
        category.setName("mockDog");
        mockedPetEntity.setName("mockPet");
        mockedPetEntity.setStatus(PetStatus.AVAILABLE);
        mockedPetEntity.setCategory(category);
        mockedPetEntity.setTags(new HashSet<>());
        mockedPetEntity.setPhotoUrls(new HashSet<>());
        PetEntity createdPet = petService.create(mockedPetEntity);

        verify(petValidator, times(1)).validate(petArgumentCaptor.capture());
        PetEntity captoredPet = petArgumentCaptor.getValue();
        Assertions.assertEquals(captoredPet.getName(), "mockPet");
        Assertions.assertEquals(captoredPet.getStatus(), PetStatus.AVAILABLE);
        CategoryEntity categoryEntity = captoredPet.getCategory();
        Assertions.assertEquals(captoredPet, mockedPetEntity);

        verify(repository).save(eq(captoredPet));

    }

    @Test
    void whenCategoryValidatorRaiseErrorsThenShouldThrowViolations() {
        when(petValidator.validate(any(PetEntity.class))).thenReturn(new HashSet<>());

        AppError error = new AppError();
        error.setField("category");
        when(categoryValidator.validate(any(CategoryEntity.class))).thenReturn(of(error));

        Set<AppError> errors = Assertions.assertThrows(ViolationException.class, () -> {
            PetEntity entity = new PetEntity();
            entity.setCategory(new CategoryEntity());
            petService.create(entity);
        }).getErrors();

        Assertions.assertEquals("category", errors.stream().findFirst().get().getField());
    }

    @Test
    void whenCalledDeleteByIdWithNotExistIdThenShouldThrowEntityNotFound() {
        when(repository.findById(anyLong())).thenThrow(new EntityNotFound());

        Assertions.assertThrows(EntityNotFound.class, () -> {
            petService.deleteById(55555L);
        });

    }
    @Test
    void whenExecutedDeleteByIdWithExistingIdThenShouldDeleteFoundEntity() {
        Long id  = 5L;
        PetEntity entity = new PetEntity();
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        petService.deleteById(id);
        verify(repository).delete(eq(entity));

    }

    @Test
    void whenExecutedDeleteByIdWithExistingIdThenShouldDeletePet() {
        Long id = 3L;
        ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);

        when(repository.findById(id)).thenReturn(Optional.of(new PetEntity()));

        petService.deleteById(id);

        verify(repository, times(1)).findById(longCaptor.capture());
        Long petId  = longCaptor.getValue();
        Assertions.assertEquals(id ,petId);
    }

}
