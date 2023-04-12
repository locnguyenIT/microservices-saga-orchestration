package com.ntloc.orderhistory;

import com.ntloc.order.OrderDetails;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.math.BigDecimal;
import java.util.UUID;

@EnableMongoRepositories
@SpringBootApplication
public class OrderHistoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderHistoryApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(OrderViewRepository orderViewRepository) {
        return args -> {
            OrderView order = new OrderView(UUID.randomUUID().toString(),
                    "orderId", OrderDetails.builder()
                    .customerId(1L)
                    .productId(2L)
                    .quantity(5)
                    .totalMoney(BigDecimal.valueOf(100_000))
                    .build(), "customerId", "Loc Nguyen");
            orderViewRepository.save(order);
        };
    }

}
