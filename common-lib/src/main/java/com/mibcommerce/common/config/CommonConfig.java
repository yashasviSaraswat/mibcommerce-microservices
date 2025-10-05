package com.mibcommerce.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Common configuration for all microservices
 */
@Configuration
public class CommonConfig {

    /**
     * RestTemplate bean for inter-service communication
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
