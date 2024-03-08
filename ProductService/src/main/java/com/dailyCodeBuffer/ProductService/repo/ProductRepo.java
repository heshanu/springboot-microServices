package com.dailyCodeBuffer.ProductService.repo;

import com.dailyCodeBuffer.ProductService.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductEntity,Long> {
}
