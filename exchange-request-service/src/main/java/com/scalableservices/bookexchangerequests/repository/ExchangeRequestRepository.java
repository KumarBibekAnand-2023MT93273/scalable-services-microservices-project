package com.scalableservices.bookexchangerequests.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scalableservices.bookexchangerequests.entity.ExchangeRequest;

@Repository
public interface ExchangeRequestRepository extends JpaRepository<ExchangeRequest, Long> {
    List<ExchangeRequest> findByOwnerId(Long ownerId);
    List<ExchangeRequest> findByRequesterId(Long requesterId);
}
