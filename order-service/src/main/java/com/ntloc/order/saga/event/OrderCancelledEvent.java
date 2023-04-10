package com.ntloc.order.saga.event;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderCancelledEvent {

    private String orderId;

}
