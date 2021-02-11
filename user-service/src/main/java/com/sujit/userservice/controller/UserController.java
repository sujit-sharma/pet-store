package com.sujit.userservice.controller;

import com.sujit.userservice.LoginDto;
import com.sujit.userservice.LoginResponse;
import com.sujit.userservice.model.User;
import com.sujit.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<User>> createAllWithList(@RequestBody List<User> users) {
        log.info("Creating many user with list");
        return ResponseEntity.ok(repository.saveAll(users));
    }
    @GetMapping("user/login")
    public ResponseEntity<Object> login(@RequestParam String username, @RequestParam String password) {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(username);
        loginDto.setPassword(password);
        User user = repository.findByUsername(loginDto.getUsername()).orElse(null);
        LoginResponse response = new LoginResponse();

        if(user != null && user.getPassword().equals(loginDto.getPassword())) {
            log.info("Login Success");
            response.setMessage("Login Success");
            return ResponseEntity.ok(response);
        }

        response.setMessage("Invalid Username or password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
    @GetMapping("/user/logout")
    public ResponseEntity<Object> logout(){
        log.info("processing logout");
        return ResponseEntity.ok().build();
    }
    @GetMapping("/user/{username}")
    public ResponseEntity<User> findByUserName(@PathVariable String username) {
        log.info("Finding user by username");
        Optional<User> exist = repository.findByUsername(username);
        if(exist.isEmpty()) {
            log.info("User does not exist");
            return ResponseEntity.notFound().build();
        }
        log.info("Successfully retrieved user");
        return ResponseEntity.ok(exist.get());
    }
    @PutMapping("/user/{username}")
    public ResponseEntity<User> update(@PathVariable String username, @RequestBody User user) {
        log.info("Updating Existing User {} ", user);
        Optional<User> exist  = repository.findByUsername(username);
        if(exist.isEmpty()) {
            log.info("Cannot Update User does not exist");
            return ResponseEntity.notFound().build();
        }
        User userForUpdate = exist.get();
        userForUpdate.setId(user.getId());
        userForUpdate.setUsername(user.getUsername());
        userForUpdate.setFirstName(user.getFirstName());
        userForUpdate.setLastName(user.getLastName());
        userForUpdate.setEmail(user.getEmail());
        userForUpdate.setPassword(user.getPassword());
        userForUpdate.setPhone(user.getPhone());
        log.info("Update user success");
        return ResponseEntity.ok(repository.save(userForUpdate));
    }
    @DeleteMapping("/user/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable String username) {
        log.info("Deleting user {} ", username);
        Optional<User> exist= repository.findByUsername(username);
        if(exist.isEmpty()) {
            log.info("User does not exist");
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(exist.get().getId());
        log.info("Deleted successfully");
        return ResponseEntity.ok().build();
    }



}


