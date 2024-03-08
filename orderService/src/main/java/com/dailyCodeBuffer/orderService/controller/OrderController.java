package com.dailyCodeBuffer.orderService.controller;

import com.dailyCodeBuffer.orderService.entity.OrderEntity;
import com.dailyCodeBuffer.orderService.model.OrderRequest;
import com.dailyCodeBuffer.orderService.model.OrderResponse;
import com.dailyCodeBuffer.orderService.service.OrderService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@Log4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOderDetails(@PathVariable long orderId){
        OrderResponse orderResponse=orderService.getOrderDetails(orderId);
        return new ResponseEntity<>(orderResponse,HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<OrderResponse>> getAllProducts() {
        List<OrderResponse> orders = orderService.findAllOrders();
        return ResponseEntity.ok(orders);
    }


    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){
          try{
              long orderId=orderService.placeOrder(orderRequest);
              log.info("order Id:{}");
              return new ResponseEntity<>(orderId, HttpStatus.OK);
          }
          catch (Exception e){
             // log.error("unable to place a order for{}",orderRequest.getProductId());
              log.error("unable to place a order for {}");
             // e.printStackTrace();
          }
          return null;

    }


}
