package com.ntloc.order.saga.event;

import com.ntloc.order.OrderDetails;
import com.ntloc.order.OrderState;
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
    private OrderState state;

}
