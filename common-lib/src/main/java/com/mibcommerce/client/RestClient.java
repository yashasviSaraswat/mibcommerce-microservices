package com.mibcommerce.common.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {

    private final RestTemplate restTemplate;

    public RestClient() {
        this.restTemplate = new RestTemplate();
    }

    public <T> T get(String url, Class<T> responseType, String token) {
        HttpHeaders headers = new HttpHeaders();
        if (token != null) {
            headers.set("Authorization", "Bearer " + token);
        }
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<T> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                responseType
        );

        return response.getBody();
    }

    public <T, R> R post(String url, T request, Class<R> responseType, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        if (token != null) {
            headers.set("Authorization", "Bearer " + token);
        }
        HttpEntity<T> entity = new HttpEntity<>(request, headers);

        ResponseEntity<R> response = restTemplate.postForEntity(url, entity, responseType);
        return response.getBody();
    }

    public <T, R> R put(String url, T request, Class<R> responseType, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        if (token != null) {
            headers.set("Authorization", "Bearer " + token);
        }
        HttpEntity<T> entity = new HttpEntity<>(request, headers);

        ResponseEntity<R> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                responseType
        );

        return response.getBody();
    }
}