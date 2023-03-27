package com.ntloc.payment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

import static com.ntloc.payment.PaymentState.APPROVED;

@Configuration
public class PaymentDataSource {

    @Bean
    CommandLineRunner commandLineRunner(PaymentRepository paymentRepository) {
        return args -> {
            Payment payment = Payment.builder()
                    .orderId(1L)
                    .state(APPROVED)
                    .createAt(LocalDateTime.now())
                    .build();
            paymentRepository.save(payment);
        };
    }
}
