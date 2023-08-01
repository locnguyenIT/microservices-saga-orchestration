package com.ntloc.orderhistory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderSummaryViewRepository extends MongoRepository<OrderSummaryView, String> {
    Optional<OrderSummaryView> findByOrders_OrderId(String orderId);

    Optional<OrderSummaryView> findByCustomer_CustomerId(String customerId);
}
