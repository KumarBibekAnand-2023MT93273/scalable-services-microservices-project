package com.scalableservices.bookexchangerequests.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/api")
public class ResiliencyController {

    @GetMapping("/resource")
    @Retry(name = "exchangeRequestService", fallbackMethod = "fallback")
    @RateLimiter(name = "exchangeRequestService", fallbackMethod = "rateLimiterFallback")
    public String getResource() {
        // Simulate random failure
        if (Math.random() > 0.5) {
            throw new RuntimeException("Service failure");
        } 
        return "Resource accessed successfully!";
    }

    public String fallback(Exception e) {
        return "Fallback: Unable to process your request.";
    }

    public String rateLimiterFallback(Exception e) {
        return "Rate Limit Exceeded: Please try again later.";
    }
}
