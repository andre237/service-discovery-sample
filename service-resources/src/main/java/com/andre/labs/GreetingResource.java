package com.andre.labs;

import org.springframework.web.bind.annotation.RequestMapping;

public interface GreetingResource {

    @RequestMapping("/greeting")
    String greeting();

}
