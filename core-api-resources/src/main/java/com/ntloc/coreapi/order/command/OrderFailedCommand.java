package com.ntloc.coreapi.order.command;

import com.ntloc.coreapi.messages.FailedReason;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record OrderFailedCommand(@TargetAggregateIdentifier String orderId, FailedReason failedReason) {
}
