package com.ntloc.orderhistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order_summary")
public class OrderSummaryView {

    @Id
    private String id;
    private CustomerView customer;
    private List<OrderView> orders;

    public OrderSummaryView(CustomerView customer, List<OrderView> orders) {
        this.customer = customer;
        this.orders = orders;
    }

    public OrderView getOrderByOrderId(String orderId) {
        return this.orders.stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst()
                .get();
    }

}
