package com.andre.labs;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("service-a")
public interface GreetingClient extends GreetingResource { }
