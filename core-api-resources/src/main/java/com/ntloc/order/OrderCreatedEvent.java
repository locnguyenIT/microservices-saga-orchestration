package com.ntloc.order;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderCreatedEvent {

    private String orderId;
    private OrderDetails orderDetails;
}
