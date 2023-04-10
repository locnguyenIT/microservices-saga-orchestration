package com.ntloc.order.saga.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RefundOrderCommand {

    @TargetAggregateIdentifier
    private String orderId;
}
