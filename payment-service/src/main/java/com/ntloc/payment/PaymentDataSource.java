package com.ntloc.payment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

import static com.ntloc.coreapi.messages.FailedReason.INSUFFICIENT_CREDIT;


@Configuration
public class PaymentDataSource {

    @Bean
    CommandLineRunner commandLineRunner(PaymentRepository paymentRepository) {
        return args -> {
            Payment succeed = new Payment(UUID.randomUUID().toString(), UUID.randomUUID().toString());
            Payment failed = new Payment(UUID.randomUUID().toString(), UUID.randomUUID().toString(), INSUFFICIENT_CREDIT);
            paymentRepository.saveAll(List.of(succeed, failed));
        };
    }
}
