package com.dailyCodeBuffer.orderService.service;

import com.dailyCodeBuffer.orderService.model.OrderRequest;
import com.dailyCodeBuffer.orderService.model.OrderResponse;

import java.util.List;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);

    List<OrderResponse> findAllOrders();
}
