package com.ntloc.order.saga.event;

import com.ntloc.order.OrderDetails;
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
