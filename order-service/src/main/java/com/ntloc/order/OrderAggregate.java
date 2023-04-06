package com.ntloc.order;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private Long id;
    private OrderState state;

    public OrderAggregate() {
    }

    @CommandHandler
    public void on(OrderCommand orderCommand) {
        AggregateLifecycle.apply(new OrderEvent(orderCommand.getId(),orderCommand.getState()));
    }

    @EventSourcingHandler
    public void on(OrderEvent orderEvent) {
        this.id = orderEvent.getOrderId();
        this.state = orderEvent.getState();
        System.out.println("Order State: "+ this.state);
    }
}
