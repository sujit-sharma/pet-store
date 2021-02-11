package com.sujit.storeservice.controller;

import com.sujit.storeservice.model.OrderEntity;
import com.sujit.storeservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store")
public class StoreController {
    private final OrderRepository repository;

    @PostMapping("/order")
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderEntity orderEntity) {
       log.info("Creating Order");
        return ResponseEntity.ok(repository.save(orderEntity));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderEntity> findByOrderId(@PathVariable Long orderId) {
        log.info("Searching order if exist");
        Optional<OrderEntity> exist  =  repository.findById(orderId);
        if(exist.isEmpty()) {
            log.info("Provided order id does not exist");
            return ResponseEntity.notFound().build();
        }
        log.info("Successfully retrieved order");
        return ResponseEntity.ok(exist.get());
    }
    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<Object> deleteById(@PathVariable Long orderId) {
        log.info("Searching order for deletion");
        if (!repository.existsById(orderId)) {
            log.info("Order not found");
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(orderId);
        log.info("Successfully deleted order");
        return ResponseEntity.ok().build();
    }
}
