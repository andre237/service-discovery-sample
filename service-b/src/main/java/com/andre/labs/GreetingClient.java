package com.andre.labs;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("service-a")
public interface GreetingClient {

    @RequestMapping("/greeting")
    String greeting();

}
