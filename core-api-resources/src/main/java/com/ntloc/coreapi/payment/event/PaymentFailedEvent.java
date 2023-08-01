package com.ntloc.coreapi.payment.event;

import com.ntloc.coreapi.messages.Reason;

public record PaymentFailedEvent(String paymentId,
                                 String orderId,
                                 Reason reason) {
}
