package com.scalableservices.bookexchangerequests.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalableservices.bookexchangerequests.entity.ExchangeRequest;
import com.scalableservices.bookexchangerequests.repository.ExchangeRequestRepository;

@Service
public class ExchangeRequestService {
    @Autowired
    private ExchangeRequestRepository repository;

    public List<ExchangeRequest> getExchangeRequests(Long userId) {
        return repository.findByOwnerId(userId);
    }

    public ExchangeRequest exchangeRequest(ExchangeRequest exchangeRequest) {
        exchangeRequest.setStatus("PENDING");
        return repository.save(exchangeRequest);
    }

    public void acceptExchangeRequest(Long requestId) {
        ExchangeRequest request = repository.findById(requestId).orElseThrow();
        request.setStatus("ACCEPTED");
        repository.save(request);
    }

    public void completeExchangeRequest(Long requestId) {
        ExchangeRequest request = repository.findById(requestId).orElseThrow();
        request.setStatus("COMPLETED");
        repository.save(request);
    }
}
