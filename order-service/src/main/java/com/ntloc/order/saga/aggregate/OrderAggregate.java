package com.ntloc.order.saga.aggregate;

import com.ntloc.order.OrderDetails;
import com.ntloc.order.OrderState;
import com.ntloc.order.saga.command.*;
import com.ntloc.order.saga.event.*;
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
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        log.info("Receive CreateOrderCommand for orderId : {}", createOrderCommand.getOrderId());
        //TODO: validate the createOrderCommand
        log.info("Start push OrderCreatedEvent.");
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(createOrderCommand.getOrderId(),
                createOrderCommand.getOrderDetails());
        AggregateLifecycle.apply(orderCreatedEvent);
        log.info("Pushed OrderCreatedEvent of orderId : {}", orderCreatedEvent.getOrderId());
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        log.info("Pull OrderCreatedEvent of orderId: {} " + orderCreatedEvent.getOrderId());
        this.orderId = orderCreatedEvent.getOrderId();
        this.orderDetails = orderCreatedEvent.getOrderDetails();
        this.state = CREATED;
        log.info("Updated OrderAggregate after OrderCreatedEvent: " + this);
    }

    @CommandHandler
    public void handle(ValidatePaymentCommand command) {
        log.info("Receive ValidatePaymentCommand for orderId : {}", command.getOrderId());
        //TODO: validate the ValidatePaymentCommand
        PaymentValidatedEvent paymentValidatedEvent = new PaymentValidatedEvent(command.getPaymentId(),
                command.getOrderId());
        AggregateLifecycle.apply(paymentValidatedEvent);
        log.info("Pushed PaymentValidatedEvent of orderId : {}", paymentValidatedEvent.getOrderId());
    }

    @EventSourcingHandler
    public void on(PaymentValidatedEvent event) {
        log.info("Pull PaymentValidatedEvent of paymentId: {}: " + event.getPaymentId());
        this.orderId = event.getOrderId();
        this.state = PAID;
        log.info("Updated OrderAggregate after PaymentValidatedEvent: " + this);
    }

    //TODO: Handle  UpdateProduct(Command/Event)

    @CommandHandler
    public void handle(DeliveryOrderCommand command) {
        log.info("Receive DeliveryOrderCommand for orderId : {}", command.getOrderId());
        OrderDeliveredEvent orderDeliveredEvent = new OrderDeliveredEvent(command.getDeliveryId(),
                command.getOrderId());
        AggregateLifecycle.apply(orderDeliveredEvent);
        log.info("Pushed DeliveryOrderEvent of orderId : {}", orderDeliveredEvent.getOrderId());
    }

    @EventSourcingHandler
    public void on(OrderDeliveredEvent event) {
        log.info("Pull OrderDeliveredEvent of orderId: {}: " + event.getOrderId());
        this.orderId = event.getOrderId();
        this.state = DELIVERED;
        log.info("Updated OrderAggregate after OrderDeliveredEvent: " + this);
    }

    @CommandHandler
    public void handle(CompleteOrderCommand command) {
        log.info("Receive CompleteOrderCommand for orderId : {}", command.getOrderId());
        OrderCompletedEvent orderCompletedEvent = new OrderCompletedEvent(command.getOrderId());
        AggregateLifecycle.apply(orderCompletedEvent);
        log.info("Pushed CompleteOrderEvent of orderId : {}", orderCompletedEvent.getOrderId());
    }

    @EventSourcingHandler
    public void on(OrderCompletedEvent event) {
        log.info("Pull CompleteOrderEvent of orderId: {}: " + event.getOrderId());
        this.orderId = event.getOrderId();
        this.state = COMPLETED;
        log.info("Updated OrderAggregate after CompleteOrderEvent: " + this);
    }

    @CommandHandler
    public void handle(CancelOrderCommand command) {
        log.info("Receive CancelOrderCommand of orderId : {}", command.getOrderId());
        OrderCancelledEvent orderCancelledEvent = new OrderCancelledEvent(command.getOrderId());
        AggregateLifecycle.apply(orderCancelledEvent);
        log.info("Pushed CancelOrderEvent of orderId : {}", orderCancelledEvent.getOrderId());
    }

    @EventSourcingHandler
    public void on(OrderCancelledEvent event) {
        log.info("Pull OrderCancelledEvent of orderId: {}: " + event.getOrderId());
        this.orderId = event.getOrderId();
        this.state = CANCELLED;
        log.info("Updated OrderAggregate after OrderCancelledEvent: " + this);
    }

    @CommandHandler
    public void handle(RefundOrderCommand command) {
        log.info("Receive ReturnOrderCommand of orderId : {}", command.getOrderId());
        //TODO: Validate ReturnOrderCommand (order expired, etc)
        OrderRefundedEvent orderRefundedEvent = new OrderRefundedEvent(command.getOrderId());
        AggregateLifecycle.apply(orderRefundedEvent);
        log.info("Pushed OrderReturnedEvent of orderId : {}", orderRefundedEvent.getOrderId());
    }

    @EventSourcingHandler
    public void on(OrderRefundedEvent event) {
        log.info("Pull OrderReturnedEvent of orderId: {}: " + event.getOrderId());
        this.orderId = event.getOrderId();
        this.state = REFUNDED;
        log.info("Updated OrderAggregate after OrderReturnedEvent: " + this);
    }

}
