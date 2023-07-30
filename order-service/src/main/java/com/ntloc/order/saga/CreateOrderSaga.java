package com.ntloc.order.saga;

import com.ntloc.coreapi.delivery.command.DeliveryOrderCommand;
import com.ntloc.coreapi.delivery.event.OrderDeliveredEvent;
import com.ntloc.coreapi.order.command.CompleteOrderCommand;
import com.ntloc.coreapi.order.event.OrderCompletedEvent;
import com.ntloc.coreapi.order.event.OrderCreatedEvent;
import com.ntloc.coreapi.payment.command.PaymentOrderCommand;
import com.ntloc.coreapi.payment.event.PaymentSucceededEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

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
        PaymentOrderCommand paymentOrderCommand = PaymentOrderCommand.builder()
                .paymentId(UUID.randomUUID().toString())
                .orderId(event.orderId()).build();
        commandGateway.send(paymentOrderCommand);
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void on(PaymentSucceededEvent event) {
        log.info("PaymentSucceededEvent in Saga for Order Id : {}",
                event.orderId());
        DeliveryOrderCommand deliveryOrderCommand = DeliveryOrderCommand.builder()
                .deliveryId(UUID.randomUUID().toString())
                .orderId(event.orderId()).build();
        commandGateway.send(deliveryOrderCommand);
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


}
