package com.sujit.userservice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private long id;

    @NotNull
    @Length(min = 5, message = "Username must have at least 5 characters")
    @Length(max = 30, message = "Username cannot be greater than 30 characters")
    private String username;

    @NotBlank
    @Size(min = 3 , message = "First name should be minimum {min} character long")
    @Size(max = 30, message = "First name cannot be greater than {max} character")
    private String firstName;

    @NotBlank
    @Size(min = 3 , message = "Last name should be minimum {min} character long")
    @Size(max = 30, message = "Last name cannot be greater than {max} character")
    private String lastName;

    @NotBlank
    @Email(message = "Invalid Email ")
    private String email;

    @NotBlank
//    @Size(min = 5, max = 15, message = "Password should between {min} and {max} ")
    private String password;

    @Pattern(regexp="(^$|[0-9]{10})")
    private String phone;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> authorities;
}
