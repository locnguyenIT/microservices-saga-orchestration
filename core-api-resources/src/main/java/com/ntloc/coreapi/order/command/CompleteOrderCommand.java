package com.ntloc.coreapi.order.command;

import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
public record CompleteOrderCommand(@TargetAggregateIdentifier String orderId) {

}
