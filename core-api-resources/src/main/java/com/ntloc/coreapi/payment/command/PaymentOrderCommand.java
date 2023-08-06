package com.ntloc.coreapi.payment.command;

import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Builder
public record PaymentOrderCommand(@TargetAggregateIdentifier String paymentId,
                                  String orderId,
                                  Long totalMoney,
                                  Long customerMoney) {

}
