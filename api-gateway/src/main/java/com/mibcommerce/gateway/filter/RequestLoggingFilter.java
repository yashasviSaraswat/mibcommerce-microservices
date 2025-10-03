package com.mibcommerce.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Global filter to log all incoming requests
 */
@Component
public class RequestLoggingFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        logger.info("=== Incoming Request ===");
        logger.info("Time: {}", LocalDateTime.now());
        logger.info("Method: {}", request.getMethod());
        logger.info("Path: {}", request.getPath());
        logger.info("Headers: {}", request.getHeaders());
        logger.info("Query Params: {}", request.getQueryParams());

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            logger.info("=== Response Status: {} ===",
                    exchange.getResponse().getStatusCode());
        }));
    }

    @Override
    public int getOrder() {
        return -1; // Execute before other filters
    }
}