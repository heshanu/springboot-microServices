package com.dailyCodeBuffer.orderService.serviceImpl;

import com.dailyCodeBuffer.orderService.entity.OrderEntity;
import com.dailyCodeBuffer.orderService.exception.CustomException;
import com.dailyCodeBuffer.orderService.external.externalClient.client.PaymentService;
import com.dailyCodeBuffer.orderService.external.externalClient.client.ProductService;
import com.dailyCodeBuffer.orderService.external.externalClient.request.PaymentRequest;
import com.dailyCodeBuffer.orderService.model.OrderRequest;
import com.dailyCodeBuffer.orderService.model.OrderResponse;
import com.dailyCodeBuffer.orderService.repo.OrderRepo;
import com.dailyCodeBuffer.orderService.service.OrderService;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Override
    public long placeOrder(OrderRequest orderRequest) {
        //order entity save dta with status order created
       // logger.info(new ParameterizedMessage("placed a order with id {}",orderRequest.getProductId()));
        //product service from product micro service
        productService.reducedQuantity(orderRequest.getProductId(),orderRequest.getQuantity());

        log.info("Created Order with Status Created ");

        OrderEntity order=OrderEntity.builder()
                .amount(orderRequest.getTotalAmount())
                .orderState("CRATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();
        orderRepo.save(order);
      //  logger.info(new ParameterizedMessage("placed a order with id {}",orderRequest.getProductId()));
    //    log.info(new ParameterizedMessage("placed a order with id '{}'", orderRequest.getProductId()));

        //product service-block products reduced the quantity

        //payment service-payment succes else cancel
        log.info("Calling payment Service to complete the payment!");
        PaymentRequest paymentRequest=new PaymentRequest().builder()
                .paymentMode(orderRequest.getPaymentMode())
                .orderId(order.getId())
                .amount(orderRequest.getTotalAmount())
                .build();

        String orderStatus=null;
        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment done Successfully Changing the order status ");
            orderStatus="PLACED";
        }catch (Exception e){
            log.error("Error occured in payment.()Changing order status failed");
            orderStatus="PAYMENT_FAILED";
        }
        order.setOrderState(orderStatus);
        orderRepo.save(order);
        log.info("end of payment service!");

        return order.getId();
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        log.info("Get order details for orderID :{}");

      OrderEntity order=orderRepo.findById(orderId)
                .orElseThrow(() -> new CustomException("order not found"+orderId,"NOT_FOUND",404));

        OrderResponse orderResponse=OrderResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderState())
                .amount(order.getAmount())
                .orderDate(order.getOrderDate())
                .build();

        return orderResponse;
    }

    @Override
    public List<OrderResponse> findAllOrders() {
        List<OrderEntity> products = orderRepo.findAll();
        List<OrderResponse> productDTOs = new ArrayList<>();
        for (OrderEntity product : products) {
            productDTOs.add(mapToDTO(product));
        }
        return productDTOs;
    }

    private OrderResponse mapToDTO(OrderEntity order) {
        return new OrderResponse(
               order.getId(),order.getOrderDate(),order.getOrderState(),order.getAmount()
        );

    }
}
