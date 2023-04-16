package com.ntloc.orderhistory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableMongoRepositories
@SpringBootApplication
public class OrderHistoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderHistoryApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(OrderSummaryViewRepository orderSummaryViewRepository) {
//        return args -> {
//            OrderLineItem line = OrderLineItem.builder()
//                    .productId(2L)
//                    .productName("fewfewfwefe")
//                    .quantity(2)
//                    .price(BigDecimal.valueOf(2000)).build();
//            OrderView order = OrderView.builder()
//                    .orderId(UUID.randomUUID().toString())
//                    .lineItems(Arrays.asList(line))
//                    .moneyTotal(BigDecimal.TEN)
//                    .build();
//            CustomerView customer = CustomerView.builder()
//                    .customerId(UUID.randomUUID().toString())
//                    .name("Loc Nguyen").build();
//
//            OrderSummaryView orderSummaryView = OrderSummaryView.builder()
//                    .customer(customer)
//                    .orders(order).build();
//
//            OrderSummaryView insert = orderSummaryViewRepository.insert(orderSummaryView);
//            System.out.println(insert);
//        };
//    }

}
