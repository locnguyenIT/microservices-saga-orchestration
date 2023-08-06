package com.ntloc.coreapi.payment.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CancelPaymentCommand(@TargetAggregateIdentifier String paymentId,
                                   String orderId) {
}
