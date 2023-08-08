package com.ntloc.coreapi.payment.command;

import com.ntloc.coreapi.messages.FailedReason;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record PaymentFailedCommand(@TargetAggregateIdentifier String paymentId,
                                   String orderId,
                                   FailedReason failedReason) {
}
