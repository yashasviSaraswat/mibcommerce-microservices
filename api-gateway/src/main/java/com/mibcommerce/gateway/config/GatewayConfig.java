package com.mibcommerce.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Additional route configuration if needed
 * Routes are primarily defined in application.yml
 */
@Configuration
public class GatewayConfig {

    // Custom route filters can be added here
    // Example: Request/Response logging, custom authentication, etc.

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Health check route for gateway itself
                .route("gateway-health", r -> r
                        .path("/health")
                        .uri("http://localhost:8080"))
                .build();
    }
}