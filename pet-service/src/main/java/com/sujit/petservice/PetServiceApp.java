package com.sujit.petservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.sujit.petservice.*"})
public class PetServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(PetServiceApp.class, args);
    }
}
