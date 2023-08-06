package com.ntloc.order.saga;

import com.ntloc.coreapi.delivery.command.DeliveryOrderCommand;
import com.ntloc.coreapi.delivery.event.OrderDeliveredEvent;
import com.ntloc.coreapi.order.command.CancelOrderCommand;
import com.ntloc.coreapi.order.command.CompleteOrderCommand;
import com.ntloc.coreapi.order.event.OrderCancelledEvent;
import com.ntloc.coreapi.order.event.OrderCompletedEvent;
import com.ntloc.coreapi.order.event.OrderCreatedEvent;
import com.ntloc.coreapi.payment.command.CancelPaymentCommand;
import com.ntloc.coreapi.payment.command.PaymentOrderCommand;
import com.ntloc.coreapi.payment.event.PaymentCanceledEvent;
import com.ntloc.coreapi.payment.event.PaymentFailedEvent;
import com.ntloc.coreapi.payment.event.PaymentSucceededEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.deadline.DeadlineManager;
import org.axonframework.deadline.annotation.DeadlineHandler;
import org.axonframework.messaging.Scope;
import org.axonframework.messaging.ScopeDescriptor;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.UUID;

@Slf4j
@Saga
public class CreateOrderSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void on(OrderCreatedEvent event) {
        log.info("OrderCreatedEvent in Saga for Order Id : {}",
                event.orderId());
        PaymentOrderCommand paymentOrderCommand = new PaymentOrderCommand(UUID.randomUUID().toString(), event.orderId());
        commandGateway.send(paymentOrderCommand)
                .exceptionally(ex -> {
                    CancelOrderCommand cancelOrderCommand = new CancelOrderCommand(event.orderId());
                    commandGateway.send(cancelOrderCommand);
                    return ex;
                });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void on(PaymentSucceededEvent event) {
        log.info("PaymentSucceededEvent in Saga for Order Id : {}",
                event.orderId());

        DeliveryOrderCommand deliveryOrderCommand = DeliveryOrderCommand.builder()
                .deliveryId(UUID.randomUUID().toString())
                .orderId(event.orderId()).build();
        commandGateway.send(deliveryOrderCommand)
                .exceptionally(ex -> {
                    CancelPaymentCommand cancelPaymentCommand = new CancelPaymentCommand(event.paymentId(),event.orderId());
                    commandGateway.send(cancelPaymentCommand);
                    return ex;
                });
    }


    @SagaEventHandler(associationProperty = "orderId")
    public void on(OrderDeliveredEvent event) {
        log.info("OrderDeliveredEvent in Saga for Order Id : {}",
                event.orderId());
        CompleteOrderCommand completeOrderCommand = CompleteOrderCommand.builder()
                .orderId(event.orderId()).build();
        commandGateway.send(completeOrderCommand);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void on(OrderCompletedEvent event) {
        log.info("OrderCompletedEvent in Saga for Order Id : {}",
                event.orderId());
        //TODO: Send notification to notification service
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void on(OrderCancelledEvent event) {
        log.info("OrderCancelledEvent in Saga for Order Id : {}",
                event.orderId());
        //TODO: Send notification to notification service
    }


    @SagaEventHandler(associationProperty = "orderId")
    public void on(PaymentCanceledEvent event) {
        log.info("PaymentCanceledEvent in Saga for Order Id : {}",
                event.orderId());
        CancelOrderCommand cancelOrderCommand = new CancelOrderCommand(event.orderId());
        commandGateway.send(cancelOrderCommand);
    }

}
