package com.ntloc.coreapi.order.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CancelOrderCommand(@TargetAggregateIdentifier String orderId, String customerId) {


}
