package com.dailyCodeBuffer.orderService.service;

import com.dailyCodeBuffer.orderService.model.OrderRequest;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);
}
