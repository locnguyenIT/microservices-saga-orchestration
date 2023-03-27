package com.ntloc.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class ProductDataSource {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            Product chalua = Product.builder()
                    .name("Cha lua")
                    .quantity(5)
                    .price(BigDecimal.valueOf(100_000)).build();
            Product nemchua = Product.builder()
                    .name("Nem chua")
                    .quantity(10)
                    .price(BigDecimal.valueOf(50_000)).build();
            productRepository.saveAll(List.of(chalua, nemchua));
        };
    }
}
