package com.dailyCodeBuffer.orderService.controller;

import com.dailyCodeBuffer.orderService.entity.OrderEntity;
import com.dailyCodeBuffer.orderService.model.OrderRequest;
import com.dailyCodeBuffer.orderService.service.OrderService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@Log4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){
          try{
              long orderId=orderService.placeOrder(orderRequest);
              log.info("order Id:{}");
              return new ResponseEntity<>(orderId, HttpStatus.OK);
          }
          catch (Exception e){
              e.printStackTrace();
          }
          return null;

    }


}
