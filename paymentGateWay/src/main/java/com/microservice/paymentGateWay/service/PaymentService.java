package com.microservice.paymentGateWay.service;

import com.microservice.paymentGateWay.model.PaymentRequest;

import java.util.List;

public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);
//    Long addProduct(ProductRequest request);
//
//    List<ProductResponse> getAllProducts();
//
//    ProductResponse getProductById(long productId);
//
//    void reducedQuantity(long productId, long quantity);
}
