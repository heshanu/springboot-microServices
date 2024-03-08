package com.dailyCodeBuffer.ProductService.service;

import com.dailyCodeBuffer.ProductService.model.ProductRequest;
import com.dailyCodeBuffer.ProductService.model.ProductResponse;

import java.util.List;

public interface ProductService {
    Long addProduct(ProductRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(long productId);
}
