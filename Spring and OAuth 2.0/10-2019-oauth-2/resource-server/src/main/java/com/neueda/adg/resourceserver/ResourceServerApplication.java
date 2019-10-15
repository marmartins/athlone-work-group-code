package com.neueda.adg.resourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/services")
@EnableResourceServer
public class ResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }


    @SuppressWarnings("unchecked")
    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return new ArrayList() {{
            add(new Person("David", "Renton", 25, ""));
            add(new Person("Marcelo", "Martins", 22, ""));
            add(new Person("Foka", "Noids", 22, ""));
        }};
    }

}
