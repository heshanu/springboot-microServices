package com.microservice.paymentGateWay.repo;


import com.microservice.paymentGateWay.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionalRepo extends JpaRepository<TransactionDetails,Long> {
}
