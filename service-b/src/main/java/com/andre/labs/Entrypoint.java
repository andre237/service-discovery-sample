package com.andre.labs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Entrypoint implements CommandLineRunner {

    @Autowired
    private GreetingClient greetingClient;

    public static void main(String[] args) {
        SpringApplication.run(Entrypoint.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Contacting greetingClient....");
        System.out.println("Response = " + greetingClient.greeting());
    }
}
