package com.ntloc.orderhistory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderViewRepository extends MongoRepository<OrderView, String> {

    Optional<OrderView> findByOrderId(String orderId);

    Optional<OrderView> findByCustomerId(String customerId);
}
