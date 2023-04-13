package com.ntloc.coreapi.payment.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record PaymentSucceededEvent(@TargetAggregateIdentifier String paymentId,
                                    String orderId) {

}
