package com.ntloc.order.saga.command;

import com.ntloc.order.OrderDetails;
import com.ntloc.order.OrderState;
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
    private OrderState state;
}
