package com.dailyCodeBuffer.orderService.external.externalClient.client;

import com.dailyCodeBuffer.orderService.external.externalClient.request.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT-SERVICE/api/v1/payment")
public interface PaymentService {
    @PostMapping("/add")
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);
}
