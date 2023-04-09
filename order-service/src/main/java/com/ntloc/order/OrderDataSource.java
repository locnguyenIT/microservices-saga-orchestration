package com.ntloc.order;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.ntloc.order.OrderState.CREATED;

@Configuration
public class OrderDataSource {

//    @Bean
//    CommandLineRunner commandLineRunner(OrderRepository orderRepository) {
//        return args -> {
//            Order orders = Order.builder()
//                    .orderDetails(OrderDetails.builder()
//                            .customerId(1L)
//                            .productId(2L)
//                            .quantity(5)
//                            .totalMoney(BigDecimal.valueOf(100_000))
//                            .build())
//                    .state(CREATED)
//                    .build();
//            orderRepository.save(orders);
//        };
//    }
}
