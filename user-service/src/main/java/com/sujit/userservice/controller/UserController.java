package com.sujit.userservice.controller;

import com.sujit.userservice.model.User;
import com.sujit.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repository;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        log.info("Creating User");
        User created = repository.save(user);
        log.info("User Created Successfully");
        return ResponseEntity.ok(created);
    }

    @PostMapping("/user/createWithArray")
    public ResponseEntity<List<User>> createAll(@RequestBody User[] users) {
        log.info("Creating Many User");
        Iterable<User> userIterable = Arrays.asList(users);
        return ResponseEntity.ok(repository.saveAll(userIterable));
    }
    @PostMapping("/user/createWithList")
    public ResponseEntity<List<User>> createAllWithList(@RequestBody User[] users) {
        log.info("Creating many user with list");
        Iterable<User> userIterable = Arrays.asList(users);
        return ResponseEntity.ok(repository.saveAll(userIterable));
    }
}

