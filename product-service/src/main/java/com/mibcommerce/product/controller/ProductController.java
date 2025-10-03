package com.mibcommerce.product.controller;

import com.mibcommerce.dto.ApiResponse;
import com.mibcommerce.product.model.Brand;
import com.mibcommerce.product.model.Product;
import com.mibcommerce.product.model.Type;
import com.mibcommerce.product.repository.BrandRepository;
import com.mibcommerce.product.repository.ProductRepository;
import com.mibcommerce.product.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final TypeRepository typeRepository;

    @GetMapping
    @Cacheable("products")
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts(
            @RequestParam(required = false) Integer typeId,
            @RequestParam(required = false) Integer brandId) {

        List<Product> products;

        if (typeId != null && brandId != null) {
            products = productRepository.findByProductTypeIdAndProductBrandId(typeId, brandId);
        } else if (typeId != null) {
            products = productRepository.findByProductTypeId(typeId);
        } else if (brandId != null) {
            products = productRepository.findByProductBrandId(brandId);
        } else {
            products = productRepository.findAll();
        }

        return ResponseEntity.ok(ApiResponse.success(products));
    }

    @GetMapping("/{id}")
    @Cacheable(value = "product", key = "#id")
    public ResponseEntity<ApiResponse<Product>> getProduct(@PathVariable Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return ResponseEntity.ok(ApiResponse.success(product));
    }

    @GetMapping("/brands")
    public ResponseEntity<ApiResponse<List<Brand>>> getAllBrands() {
        return ResponseEntity.ok(ApiResponse.success(brandRepository.findAll()));
    }

    @GetMapping("/types")
    public ResponseEntity<ApiResponse<List<Type>>> getAllTypes() {
        return ResponseEntity.ok(ApiResponse.success(typeRepository.findAll()));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Product Service is running");
    }
}