package com.ntloc.coreapi.delivery.command;

import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
public record DeliveryOrderCommand(@TargetAggregateIdentifier String deliveryId,
                                   String orderId) {

}
