package com.ntloc.order;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderEvent {

    private Long orderId;
    private OrderState state;

}
