package com.ntloc.coreapi.payment.event;

public record PaymentCanceledEvent(String paymentId, String orderId) {
}
