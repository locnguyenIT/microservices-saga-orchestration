package com.ntloc.coreapi.order.event;

public record OrderRefundedEvent(String orderId, String customerId) {

}
