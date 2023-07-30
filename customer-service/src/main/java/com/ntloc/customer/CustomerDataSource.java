package com.ntloc.customer;

import com.ntloc.coreapi.customer.model.Gender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

@Configuration
public class CustomerDataSource {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            Customer loc = Customer.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Nguyen Thanh Loc")
                    .email("loc.nguyenthanh2@vn.bosch.com")
                    .gender(Gender.MALE)
                    .build();

            customerRepository.saveAll(List.of(loc));
        };
    }
}
