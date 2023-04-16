package com.ntloc.order;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

@Configuration
public class OrderDataSource {

    @Bean
    CommandLineRunner commandLineRunner(OrderRepository orderRepository) {
        return args -> {
            OrderLineItem line = OrderLineItem.builder()
                    .productId(2L)
                    .productName("abc")
                    .quantity(2)
                    .price(BigDecimal.valueOf(2000)).build();
            Order order = Order.builder()
                    .id(UUID.randomUUID().toString())
                    .customerId("customerId")
                    .lineItems(Arrays.asList(line))
                    .moneyTotal(BigDecimal.TEN)
                    .build();
            orderRepository.save(order);
        };
    }
}
