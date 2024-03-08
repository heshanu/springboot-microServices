package com.dailyCodeBuffer.orderService.serviceImpl;

import com.dailyCodeBuffer.orderService.entity.OrderEntity;
import com.dailyCodeBuffer.orderService.model.OrderRequest;
import com.dailyCodeBuffer.orderService.repo.OrderRepo;
import com.dailyCodeBuffer.orderService.service.OrderService;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    @Override
    public long placeOrder(OrderRequest orderRequest) {
        //order entity save dta with status order created
        logger.info(new ParameterizedMessage("placed a order with id {}",orderRequest.getProductId()));
        OrderEntity order=OrderEntity.builder()
                .amount(orderRequest.getTotalAmount())
                .orderState("CRATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();
        orderRepo.save(order);
        logger.info(new ParameterizedMessage("placed a order with id {}",orderRequest.getProductId()));

        //product service-block products reduced the quantity
        //payment service-payment succes else cancel
        return order.getId();
    }
}
