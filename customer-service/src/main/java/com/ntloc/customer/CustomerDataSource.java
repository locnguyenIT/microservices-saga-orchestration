package com.ntloc.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class CustomerDataSource {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            Customer username = Customer.builder()
                    .name("username")
                    .email("username@gmail.com")
                    .gender(Gender.MALE)
                    .money(BigDecimal.TEN).build();
            Customer loc = Customer.builder()
                    .name("Nguyen Thanh Loc")
                    .email("loc.nguyenthanh2@vn.bosch.com")
                    .gender(Gender.FEMALE)
                    .money(BigDecimal.ONE).build();

            customerRepository.saveAll(List.of(username, loc));
        };
    }
}
