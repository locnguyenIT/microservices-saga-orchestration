package com.ntloc.coreapi.delivery.event;

public record OrderDeliveredEvent(String deliveryId,
                                  String orderId) {

}
