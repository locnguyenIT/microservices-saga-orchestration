package com.ntloc.payment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class PaymentDataSource {

    @Bean
    CommandLineRunner commandLineRunner(PaymentRepository paymentRepository) {
        return args -> {
            Payment payment = new Payment(UUID.randomUUID().toString(), UUID.randomUUID().toString());
            paymentRepository.save(payment);
        };
    }
}
