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

    /**
     * GET request with optional token
     */
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

    /**
     * GET request without token
     */
    public <T> T get(String url, Class<T> responseType) {
        return get(url, responseType, null);
    }

    /**
     * POST request with optional token
     */
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

    /**
     * POST request without token
     */
    public <T, R> R post(String url, T request, Class<R> responseType) {
        return post(url, request, responseType, null);
    }

    /**
     * PUT request with optional token
     */
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

    /**
     * PUT request without token
     */
    public <T, R> R put(String url, T request, Class<R> responseType) {
        return put(url, request, responseType, null);
    }

    /**
     * DELETE request with optional token
     */
    public void delete(String url, String token) {
        HttpHeaders headers = new HttpHeaders();
        if (token != null) {
            headers.set("Authorization", "Bearer " + token);
        }
        HttpEntity<String> entity = new HttpEntity<>(headers);

        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                entity,
                Void.class
        );
    }

    /**
     * DELETE request without token
     */
    public void delete(String url) {
        delete(url, null);
    }
}
