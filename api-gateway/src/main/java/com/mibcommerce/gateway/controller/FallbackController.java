package com.mibcommerce.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Fallback endpoints when services are down
 */
@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/user-service")
    public ResponseEntity<Map<String, String>> userServiceFallback() {
        Map<String, String> response = new HashMap<>();
        response.put("error", "User service is currently unavailable. Please try again later.");
        response.put("service", "user-service");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @GetMapping("/product-service")
    public ResponseEntity<Map<String, String>> productServiceFallback() {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Product service is currently unavailable. Please try again later.");
        response.put("service", "product-service");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @GetMapping("/order-service")
    public ResponseEntity<Map<String, String>> orderServiceFallback() {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Order service is currently unavailable. Please try again later.");
        response.put("service", "order-service");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @GetMapping("/payment-service")
    public ResponseEntity<Map<String, String>> paymentServiceFallback() {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Payment service is currently unavailable. Please try again later.");
        response.put("service", "payment-service");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }
}
