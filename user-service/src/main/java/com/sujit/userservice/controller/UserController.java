package com.sujit.userservice.controller;

import com.sujit.userservice.LoginDto;
import com.sujit.userservice.LoginResponse;
import com.sujit.userservice.model.Authorities;
import com.sujit.userservice.model.UserEntity;
import com.sujit.userservice.repository.UserRepository;
import com.sujit.userservice.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repository;
    private final AuthenticationManagerBuilder authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @PostMapping("/user")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        log.info("Creating User");
        Authorities.validate(user.getAuthorities());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity created = repository.save(user);
        log.info("User Created Successfully");
        return ResponseEntity.ok(created);
    }

    @PostMapping("/user/createWithArray")
    public ResponseEntity<List<UserEntity>> createAll(@RequestBody UserEntity[] users) {
        log.info("Creating Many User");
        Iterable<UserEntity> userIterable = Arrays.asList(users);

        userIterable.forEach(entity -> {
            Authorities.validate(entity.getAuthorities());
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        });

        return ResponseEntity.ok(repository.saveAll(userIterable));
    }

    @PostMapping("/user/createWithList")
    public ResponseEntity<List<UserEntity>> createAllWithList(@RequestBody List<UserEntity> users) {
        log.info("Creating many user with list");
        users.forEach(entity ->
        {
            Authorities.validate(entity.getAuthorities());
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        });
        return ResponseEntity.ok(repository.saveAll(users));
    }

    @GetMapping("user/login")
    public ResponseEntity<Object> login(@RequestParam String username, @RequestParam String password) {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(username);
        loginDto.setPassword(password);
        log.info("Signing  in user {} ", loginDto);

        final LoginResponse response = new LoginResponse();

        try {
            Authentication authentication = authenticationManager.getObject().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenProvider.getToken();
            response.setToken(token);
            response.setMessage("Login successful");
        } catch (AuthenticationException e) {
            log.info("Invalid user/ password . Sign in Unsuccessful");
            response.setMessage("Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        log.info("User login success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/logout")
    public ResponseEntity<Object> logout() {
        log.info("processing logout");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserEntity> findByUserName(@PathVariable String username) {
        log.info("Finding user by username");
        Optional<UserEntity> exist = repository.findByUsername(username);
        if (exist.isEmpty()) {
            log.info("User does not exist");
            return ResponseEntity.notFound().build();
        }
        log.info("Successfully retrieved user");
        return ResponseEntity.ok(exist.get());
    }

    @PutMapping("/user/{username}")
    public ResponseEntity<UserEntity> update(@PathVariable String username, @RequestBody UserEntity user) {
        log.info("Updating Existing User {} ", user);
        Optional<UserEntity> exist = repository.findByUsername(username);
        if (exist.isEmpty()) {
            log.info("Cannot Update User does not exist");
            return ResponseEntity.notFound().build();
        }
        UserEntity userForUpdate = exist.get();
        userForUpdate.setId(user.getId());
        userForUpdate.setUsername(user.getUsername());
        userForUpdate.setFirstName(user.getFirstName());
        userForUpdate.setLastName(user.getLastName());
        userForUpdate.setEmail(user.getEmail());
        userForUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        userForUpdate.setPhone(user.getPhone());
        log.info("Update user success");
        return ResponseEntity.ok(repository.save(userForUpdate));
    }

    @DeleteMapping("/user/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable String username) {
        log.info("Deleting user {} ", username);
        Optional<UserEntity> exist = repository.findByUsername(username);
        if (exist.isEmpty()) {
            log.info("User does not exist");
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(exist.get().getId());
        log.info("Deleted successfully");
        return ResponseEntity.ok().build();
    }


}


