package com.ntloc.coreapi.order.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record RefundOrderCommand(@TargetAggregateIdentifier String orderId) {

}
