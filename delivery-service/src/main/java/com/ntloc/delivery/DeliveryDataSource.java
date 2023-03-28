package com.ntloc.delivery;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DeliveryDataSource {

    @Bean
    CommandLineRunner commandLineRunner(DeliveryRepository customerRepository) {
        return args -> {
            Delivery delivery = new Delivery(1L, LocalDateTime.of(2023, 05, 14, 05, 34, 12));


            customerRepository.saveAll(List.of(delivery));
        };
    }
}
