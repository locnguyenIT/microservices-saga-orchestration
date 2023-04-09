package com.ntloc.order.saga.aggregate;

import com.ntloc.order.OrderDetails;
import com.ntloc.order.OrderState;
import com.ntloc.order.saga.command.CreateOrderCommand;
import com.ntloc.order.saga.event.OrderCreatedEvent;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@ToString
@Slf4j
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private OrderDetails orderDetails;
    private OrderState state;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        //TODO: validate the createOrderCommand
        log.info("Start push OrderCreatedEvent.");
        AggregateLifecycle.apply(new OrderCreatedEvent(createOrderCommand.getOrderId(),
                createOrderCommand.getOrderDetails(),
                createOrderCommand.getState()));
        log.info("Pushed OrderCreatedEvent.");
    }

//    @CommandHandler
//    public void on(CreateOrderCommand createOrderCommand) {
//        AggregateLifecycle.apply(new OrderCreatedEvent(createOrderCommand.getOrderId(),
//                createOrderCommand.getOrderDetails(),
//                createOrderCommand.getState()));
//    }
    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        log.info("Pull OrderCreateEvent: "+ orderCreatedEvent.toString());
        this.orderId = orderCreatedEvent.getOrderId();
        this.orderDetails = orderCreatedEvent.getOrderDetails();
        this.state = orderCreatedEvent.getState();
        log.info("Updated OrderAggregate: "+this.toString());
    }
}
