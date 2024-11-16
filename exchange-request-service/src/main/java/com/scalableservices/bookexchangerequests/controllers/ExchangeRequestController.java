package com.scalableservices.bookexchangerequests.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scalableservices.bookexchangerequests.entity.ExchangeRequest;
import com.scalableservices.bookexchangerequests.service.ExchangeRequestService;

@RestController
@RequestMapping("/api")
public class ExchangeRequestController {
	
    @Autowired
    private ExchangeRequestService service;

    @GetMapping("/exchange-requests/{userId}")
    public List<ExchangeRequest> getExchangeRequests(@PathVariable Long userId) {
        return service.getExchangeRequests(userId);
    }

    @PostMapping("/exchange-requests")
    public ExchangeRequest storeExchangeRequests(@RequestBody ExchangeRequest request) {
        return service.exchangeRequest(request);
    }

    @PutMapping("/exchange-requests/{exchangeRequestId}/accept")
    public ResponseEntity<?> acceptExchangeRequest(@PathVariable Long exchangeRequestId) {
        service.acceptExchangeRequest(exchangeRequestId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/exchange-requests/{exchangeRequestId}/complete")
    public ResponseEntity<?> completeExchangeRequest(@PathVariable Long exchangeRequestId) {
        service.completeExchangeRequest(exchangeRequestId);
        return ResponseEntity.ok().build();
    }
}
