package com.sujit.petservice.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.xml.transform.Source;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class CategoryEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Category name cannot be empty")
    private String name;

}
