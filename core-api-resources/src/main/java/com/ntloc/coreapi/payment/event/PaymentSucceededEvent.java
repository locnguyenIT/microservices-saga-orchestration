package com.ntloc.coreapi.payment.event;

public record PaymentSucceededEvent(String paymentId,
                                    String orderId) {

}
