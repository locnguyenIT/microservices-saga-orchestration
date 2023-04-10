package com.ntloc.order.saga.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CancelOrderCommand {

    @TargetAggregateIdentifier
    private String orderId;
}
