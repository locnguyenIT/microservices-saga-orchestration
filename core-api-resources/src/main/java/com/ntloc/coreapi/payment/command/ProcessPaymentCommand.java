package com.ntloc.coreapi.payment.command;

import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
public record ProcessPaymentCommand(@TargetAggregateIdentifier String paymentId,
                                    String orderId) {

}
