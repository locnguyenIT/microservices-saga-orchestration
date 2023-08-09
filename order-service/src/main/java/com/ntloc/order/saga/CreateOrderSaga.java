package com.ntloc.order.saga;

import com.ntloc.coreapi.customer.query.FetchCustomerMoneyQuery;
import com.ntloc.coreapi.delivery.command.DeliveryOrderCommand;
import com.ntloc.coreapi.delivery.event.OrderDeliveredEvent;
import com.ntloc.coreapi.messages.FailedReason;
import com.ntloc.coreapi.order.command.CompleteOrderCommand;
import com.ntloc.coreapi.order.command.OrderFailedCommand;
import com.ntloc.coreapi.order.event.OrderCompletedEvent;
import com.ntloc.coreapi.order.event.OrderCreatedEvent;
import com.ntloc.coreapi.order.event.OrderFailedEvent;
import com.ntloc.coreapi.payment.command.PaymentOrderCommand;
import com.ntloc.coreapi.payment.event.PaymentFailedEvent;
import com.ntloc.coreapi.payment.event.PaymentSucceededEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Saga
public class CreateOrderSaga {

    @Autowired
    private transient CommandGateway commandGateway;
    @Autowired
    private transient QueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void on(OrderCreatedEvent event) {
        log.info("OrderCreatedEvent in Saga for Order Id : {}",
                event.orderId());
        FetchCustomerMoneyQuery fetchCustomerMoneyQuery = new FetchCustomerMoneyQuery(event.orderDetails().customerId());

        CompletableFuture<Long> customerMoney = queryGateway.query(fetchCustomerMoneyQuery, ResponseTypes.instanceOf(Long.class));

        PaymentOrderCommand paymentOrderCommand = new PaymentOrderCommand(UUID.randomUUID().toString(), event.orderId(), event.orderDetails().totalMoney(), customerMoney.join());
        commandGateway.send(paymentOrderCommand)
                .exceptionally(ex -> {
                    //TODO: Handle exception occur payment-service
                    failedOrder(event.orderId(), FailedReason.SERVICE_EXCEPTION_OCCUR);
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
                    //TODO: Handle exception occur delivery-service
                    failedOrder(event.orderId(), FailedReason.SERVICE_EXCEPTION_OCCUR);
                    return ex;
                });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void on(PaymentFailedEvent event) {
        log.info("PaymentFailedEvent in Saga for Order Id : {}",
                event.orderId());
        failedOrder(event.orderId(), event.failedReason());
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
    public void on(OrderFailedEvent event) {
        log.info("OrderFailedEvent in Saga for Order Id : {}",
                event.orderId());
        //TODO: Send notification to notification service
    }

    private void failedOrder(String orderId, FailedReason failedReason) {
        OrderFailedCommand orderFailedCommand = new OrderFailedCommand(orderId, failedReason);
        commandGateway.send(orderFailedCommand);
    }

}
