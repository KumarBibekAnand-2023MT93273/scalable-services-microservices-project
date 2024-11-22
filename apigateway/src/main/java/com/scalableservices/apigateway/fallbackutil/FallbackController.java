package com.scalableservices.apigateway.fallbackutil;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public String fallback() {
        return "Fallback: The service is currently unavailable. Please try again later.";
    }
}
