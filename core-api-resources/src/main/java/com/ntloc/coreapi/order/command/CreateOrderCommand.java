package com.ntloc.coreapi.order.command;

import com.ntloc.coreapi.order.model.OrderDetails;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


public record CreateOrderCommand(@TargetAggregateIdentifier String orderId, OrderDetails orderDetails) {

}
