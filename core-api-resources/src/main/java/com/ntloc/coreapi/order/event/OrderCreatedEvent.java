package com.ntloc.coreapi.order.event;

import com.ntloc.coreapi.order.model.OrderDetails;

public record OrderCreatedEvent(String orderId,
                                OrderDetails orderDetails) {

}
