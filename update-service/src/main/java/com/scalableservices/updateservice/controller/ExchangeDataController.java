package com.scalableservices.updateservice.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.scalableservices.updateservice.entity.ExchangeData;

@RestController
@RequestMapping("/api/update-service")
public class ExchangeDataController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/get-data/{ownerId}")
    public List<ExchangeData> addDataFromExchangeService(@PathVariable Long ownerId) {
        // Use the dynamic id in the URL instead of hardcoding
         String url = "http://localhost:8888/api/exchange-requests/{ownerId}"; 
         
        //gateway URI
        // String url = "http://localhost:8085/exchange/api/exchange-requests/{ownerId}";
        
        ResponseEntity<List<ExchangeData>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<ExchangeData>>() {},
            ownerId // Pass the dynamic 'ownerId' as a variable for the placeholder
        );

        return response.getBody();
    }
}
