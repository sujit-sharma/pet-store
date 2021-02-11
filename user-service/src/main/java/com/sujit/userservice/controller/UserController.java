package com.sujit.userservice.controller;

import com.sujit.userservice.LoginDto;
import com.sujit.userservice.model.User;
import com.sujit.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("user/login")
    public ResponseEntity<Object> login(@RequestParam String username, @RequestParam String password) {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(username);
        loginDto.setPassword(password);
        User user = repository.findByUsername(loginDto.getUsername()).orElse(null);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        if(user.getPassword().equals(loginDto.getPassword())) {
            log.info("Login Success");
            user.setUserStatus(1);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Invalid username or password");
    }
    @GetMapping("/user/logout")
    public ResponseEntity<Object> logout(){
        log.info("processing logout");
        List<User> loginUsers =repository.findAllByUserStatus(1);
        loginUsers.stream().forEach(user -> user.setUserStatus(0));

        repository.saveAll(loginUsers);
        return ResponseEntity.ok().build();
    }
}


