package com.ntloc.order.saga.aggregate;

import com.ntloc.coreapi.order.command.CancelOrderCommand;
import com.ntloc.coreapi.order.command.CompleteOrderCommand;
import com.ntloc.coreapi.order.command.CreateOrderCommand;
import com.ntloc.coreapi.order.command.RefundOrderCommand;
import com.ntloc.coreapi.order.event.OrderCancelledEvent;
import com.ntloc.coreapi.order.event.OrderCompletedEvent;
import com.ntloc.coreapi.order.event.OrderCreatedEvent;
import com.ntloc.coreapi.order.event.OrderRefundedEvent;
import com.ntloc.coreapi.order.model.OrderDetails;
import com.ntloc.order.OrderState;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import static com.ntloc.order.OrderState.*;

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
    public OrderAggregate(CreateOrderCommand command) {
        log.info("Receive CreateOrderCommand for orderId : {}", command.orderId());
        //TODO: validate the command
        log.info("Start push OrderCreatedEvent.");
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(command.orderId(),
                command.orderDetails());
        AggregateLifecycle.apply(orderCreatedEvent);
        log.info("Pushed OrderCreatedEvent of orderId : {}", orderCreatedEvent.orderId());
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        log.info("Pull OrderCreatedEvent of orderId: {} " + orderCreatedEvent.orderId());
        this.orderId = orderCreatedEvent.orderId();
        this.orderDetails = orderCreatedEvent.orderDetails();
        this.state = CREATED;
        log.info("Updated OrderAggregate after OrderCreatedEvent: " + this);
    }


    @CommandHandler
    public void handle(CancelOrderCommand command) {
        log.info("Receive CancelOrderCommand of orderId : {}", command.orderId());
        OrderCancelledEvent orderCancelledEvent = new OrderCancelledEvent(command.orderId());
        AggregateLifecycle.apply(orderCancelledEvent);
        log.info("Pushed CancelOrderEvent of orderId : {}", orderCancelledEvent.orderId());
    }

    @EventSourcingHandler
    public void on(OrderCancelledEvent event) {
        log.info("Pull OrderCancelledEvent of orderId: {}: " + event.orderId());
        this.orderId = event.orderId();
        this.state = CANCELLED;
        log.info("Updated OrderAggregate after OrderCancelledEvent: " + this);
    }

    @CommandHandler
    public void handle(RefundOrderCommand command) {
        log.info("Receive ReturnOrderCommand of orderId : {}", command.orderId());
        //TODO: Validate ReturnOrderCommand (order expired, etc)
        OrderRefundedEvent orderRefundedEvent = new OrderRefundedEvent(command.orderId());
        AggregateLifecycle.apply(orderRefundedEvent);
        log.info("Pushed OrderReturnedEvent of orderId : {}", orderRefundedEvent.orderId());
    }

    @EventSourcingHandler
    public void on(OrderRefundedEvent event) {
        log.info("Pull OrderReturnedEvent of orderId: {}: " + event.orderId());
        this.orderId = event.orderId();
        this.state = REFUNDED;
        log.info("Updated OrderAggregate after OrderReturnedEvent: " + this);
    }

    @CommandHandler
    public void handle(CompleteOrderCommand command) {
        log.info("Receive CompleteOrderCommand for orderId : {}", command.orderId());
        OrderCompletedEvent orderCompletedEvent = new OrderCompletedEvent(command.orderId());
        AggregateLifecycle.apply(orderCompletedEvent);
        log.info("Pushed CompleteOrderEvent of orderId : {}", orderCompletedEvent.orderId());
    }

    @EventSourcingHandler
    public void on(OrderCompletedEvent event) {
        log.info("Pull CompleteOrderEvent of orderId: {}: " + event.orderId());
        this.orderId = event.orderId();
        this.state = COMPLETED;
        log.info("Updated OrderAggregate after CompleteOrderEvent: " + this);
    }


}
