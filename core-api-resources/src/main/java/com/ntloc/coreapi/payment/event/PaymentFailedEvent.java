package com.ntloc.coreapi.payment.event;

import com.ntloc.coreapi.messages.FailedReason;

public record PaymentFailedEvent(String paymentId,
                                 String orderId,
                                 FailedReason failedReason) {
}
