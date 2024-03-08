package com.dailyCodeBuffer.orderService.repo;

import com.dailyCodeBuffer.orderService.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity,Long> {
}
