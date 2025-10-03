package com.mibcommerce.product.repository;

import com.mibcommerce.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductTypeId(Integer typeId);
    List<Product> findByProductBrandId(Integer brandId);
    List<Product> findByProductTypeIdAndProductBrandId(Integer typeId, Integer brandId);
}