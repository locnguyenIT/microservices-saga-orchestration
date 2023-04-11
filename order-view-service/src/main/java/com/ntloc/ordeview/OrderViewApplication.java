package com.ntloc.ordeview;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.UUID;

@EnableMongoRepositories
@SpringBootApplication
public class OrderViewApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderViewApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(OrderViewRepository orderViewRepository) {
        return args -> {
            Order order = new Order(UUID.randomUUID().toString(), "orderId", "customerId", "Loc Nguyen");
            orderViewRepository.save(order);
        };
    }

}
