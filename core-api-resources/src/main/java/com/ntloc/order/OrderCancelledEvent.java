package com.ntloc.order;

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
