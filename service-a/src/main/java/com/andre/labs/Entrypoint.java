package com.andre.labs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Entrypoint implements GreetingResource {

    @Value("${spring.application.name}")
    private String appName;

    public static void main(String[] args) {
        SpringApplication.run(Entrypoint.class, args);
    }

    public String greeting() {
        return String.format("Hello from '%s'!", appName);
    }

}
