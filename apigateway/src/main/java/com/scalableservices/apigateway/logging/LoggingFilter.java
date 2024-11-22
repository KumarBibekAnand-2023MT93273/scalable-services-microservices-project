package com.scalableservices.apigateway.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        logger.info("Request Path: {}", path);

        // Logging after the request is handled
        return chain.filter(exchange)
            .doOnSuccess(aVoid -> {
                logger.info("Request to {} succeeded.", path);
            })
            .doOnError(error -> {
                if (error.getMessage().contains("RateLimiter")) {
                    logger.error("Rate Limit Exceeded for {}", path);
                } else {
                    logger.error("Resiliency Error on {}: {}", path, error.getMessage());
                }
            });
    }

    @Override
    public int getOrder() {
        return -1; // High priority
    }
}
