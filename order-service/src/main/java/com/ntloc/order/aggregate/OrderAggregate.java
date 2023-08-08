package com.ntloc.order.aggregate;

import com.ntloc.coreapi.messages.FailedReason;
import com.ntloc.coreapi.messages.OrderState;
import com.ntloc.coreapi.order.command.*;
import com.ntloc.coreapi.order.event.*;
import com.ntloc.coreapi.order.model.OrderDetails;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import static com.ntloc.coreapi.messages.OrderState.*;

@Aggregate
@ToString
@Slf4j
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private OrderDetails orderDetails;
    private OrderState state;
    private FailedReason failedReason;

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

    @CommandHandler
    public void handle(PaidOrderUpdateCommand command) {
        log.info("Receive PaidOrderCommand for orderId : {}", command.orderId());
        //TODO: validate the command
        log.info("Start push OrderPaidEvent.");
        PaidOrderUpdateEvent orderPaidUpdateEvent = new PaidOrderUpdateEvent(command.orderId());
        AggregateLifecycle.apply(orderPaidUpdateEvent);
        log.info("Pushed OrderPaidEvent of orderId : {}", orderPaidUpdateEvent.orderId());
    }

    @CommandHandler
    public void handle(OrderFailedCommand command) {
        log.info("Receive OrderFailedCommand for orderId : {}", command.orderId());
        //TODO: validate the command
        log.info("Start push OrderFailedEvent.");
        OrderFailedEvent orderFailedEvent = new OrderFailedEvent(command.orderId(), command.failedReason());
        AggregateLifecycle.apply(orderFailedEvent);
        log.info("Pushed OrderFailedEvent of orderId : {}", orderFailedEvent.orderId());
    }

    @CommandHandler
    public void handle(DeliveredOrderUpdateCommand command) {
        log.info("Receive DeliveredOrderUpdateCommand for orderId : {}", command.orderId());
        //TODO: validate the command
        log.info("Start push DeliveredOrderUpdateCommand.");
        DeliveredOrderUpdateEvent deliveredOrderUpdateEvent = new DeliveredOrderUpdateEvent(command.orderId());
        AggregateLifecycle.apply(deliveredOrderUpdateEvent);
        log.info("Pushed DeliveredOrderUpdateCommand of orderId : {}", deliveredOrderUpdateEvent.orderId());
    }


    @CommandHandler
    public void handle(CancelOrderCommand command) {
        log.info("Receive CancelOrderCommand of orderId : {}", command.orderId());
        OrderCancelledEvent orderCancelledEvent = new OrderCancelledEvent(command.orderId());
        AggregateLifecycle.apply(orderCancelledEvent);
        log.info("Pushed CancelOrderEvent of orderId : {}", orderCancelledEvent.orderId());
    }

    @CommandHandler
    public void handle(RefundOrderCommand command) {
        log.info("Receive ReturnOrderCommand of orderId : {}", command.orderId());
        //TODO: Validate ReturnOrderCommand (order expired, etc)
        OrderRefundedEvent orderRefundedEvent = new OrderRefundedEvent(command.orderId());
        AggregateLifecycle.apply(orderRefundedEvent);
        log.info("Pushed OrderReturnedEvent of orderId : {}", orderRefundedEvent.orderId());
    }

    @CommandHandler
    public void handle(CompleteOrderCommand command) {
        log.info("Receive CompleteOrderCommand for orderId : {}", command.orderId());
        OrderCompletedEvent orderCompletedEvent = new OrderCompletedEvent(command.orderId());
        AggregateLifecycle.apply(orderCompletedEvent);
        log.info("Pushed CompleteOrderEvent of orderId : {}", orderCompletedEvent.orderId());
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        log.info("Receive OrderCreatedEvent of orderId: {} ", orderCreatedEvent.orderId());
        this.orderId = orderCreatedEvent.orderId();
        this.orderDetails = orderCreatedEvent.orderDetails();
        this.state = CREATED;
        log.info("Updated OrderAggregate after OrderCreatedEvent: " + this);
    }

    @EventSourcingHandler
    public void on(OrderCancelledEvent event) {
        log.info("Receive OrderCancelledEvent of orderId: {} ", event.orderId());
        this.state = CANCELLED;
        log.info("Updated OrderAggregate after OrderCancelledEvent: " + this);
    }

    @EventSourcingHandler
    public void on(OrderFailedEvent event) {
        log.info("Receive OrderFailedEvent of orderId: {} ", event.orderId());
        this.state = FAILED;
        this.failedReason = event.failedReason();
        log.info("Updated OrderAggregate after OrderFailedEvent: " + this);
    }

    @EventSourcingHandler
    public void on(PaidOrderUpdateEvent event) {
        log.info("Receive OrderPaidEvent of orderId: {} ", event.orderId());
        this.state = PAID;
        log.info("Updated OrderPaidEvent after OrderCancelledEvent: " + this);
    }

    @EventSourcingHandler
    public void on(DeliveredOrderUpdateEvent event) {
        log.info("Receive DeliveredOrderUpdateEvent of orderId: {} ", event.orderId());
        this.state = DELIVERED;
        log.info("Updated DeliveredOrderUpdateEvent after OrderCancelledEvent: " + this);
    }

    @EventSourcingHandler
    public void on(OrderRefundedEvent event) {
        log.info("Receive OrderReturnedEvent of orderId: {}: ", event.orderId());
        this.state = REFUNDED;
        log.info("Updated OrderAggregate after OrderReturnedEvent: " + this);
    }

    @EventSourcingHandler
    public void on(OrderCompletedEvent event) {
        log.info("Receive OrderCompletedEvent of orderId: {}: ", event.orderId());
        this.state = COMPLETED;
        log.info("Updated OrderAggregate after OrderCompletedEvent: " + this);
    }

}
