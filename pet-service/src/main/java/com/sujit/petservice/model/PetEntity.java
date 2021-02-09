package com.sujit.petservice.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private CategoryEntity category;

    private String name;

    @ElementCollection
    private Set<String> photoUrls;

    @OneToMany
    private Set<TagEntity> tags;

    @Enumerated(EnumType.STRING)
    private PetStatus status;

}
