package com.ntloc.coreapi.order.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record PaidOrderUpdateCommand(@TargetAggregateIdentifier String orderId) {
}
