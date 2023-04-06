package com.ntloc.order;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderCommand {
    @TargetAggregateIdentifier
    private Long id;
    private OrderDetails orderDetails;
    private OrderState state;
}
