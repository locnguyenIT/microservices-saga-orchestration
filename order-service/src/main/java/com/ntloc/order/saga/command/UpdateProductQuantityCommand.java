package com.ntloc.order.saga.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UpdateProductQuantityCommand {
    @TargetAggregateIdentifier
    private String productId;
}
