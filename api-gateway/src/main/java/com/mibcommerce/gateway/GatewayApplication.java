package com.mibcommerce.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan; // Import this

@SpringBootApplication
@ComponentScan(basePackages = {"com.mibcommerce.gateway", "com.mibcommerce.common"}) // Add this
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        System.out.println("API Gateway is running on port 8080");
    }
}
    