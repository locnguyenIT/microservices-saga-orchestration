package com.ntloc.orderhistory.axon;

import com.ntloc.coreapi.customer.event.CustomerCreatedEvent;
import com.ntloc.coreapi.delivery.event.OrderDeliveredEvent;
import com.ntloc.coreapi.order.event.OrderCancelledEvent;
import com.ntloc.coreapi.order.event.OrderCompletedEvent;
import com.ntloc.coreapi.order.event.OrderCreatedEvent;
import com.ntloc.coreapi.order.event.OrderRefundedEvent;
import com.ntloc.coreapi.payment.event.PaymentSucceededEvent;
import com.ntloc.orderhistory.OrderHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderHistoryProjection {

    private final OrderHistoryService orderHistoryService;

    public OrderHistoryProjection(OrderHistoryService orderHistoryService) {
        this.orderHistoryService = orderHistoryService;
    }

    @EventHandler
    public void on(CustomerCreatedEvent event) {
        log.info("OrderHistory receive CustomerCreatedEvent: {}", event);
        orderHistoryService.addCustomer(event);

    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        log.info("OrderHistory receive OrderCreatedEvent: {}", event);
        orderHistoryService.addOrder(event);

    }

    @EventHandler
    public void on(OrderCancelledEvent event) {
        log.info("Handle OrderCancelledEvent: {}", event);
        orderHistoryService.cancelOrder(event.orderId(), event.customerId());
    }

    @EventHandler
    public void on(PaymentSucceededEvent event) {
        log.info("OrderHistory receive PaymentSucceededEvent: {}", event);
        orderHistoryService.paidOrder(event.orderId());
    }

    @EventHandler
    public void on(OrderDeliveredEvent event) {
        log.info("OrderHistory receive OrderDeliveredEvent: {}", event);
        orderHistoryService.deliveryOrder(event.orderId());
    }

    @EventHandler
    public void on(OrderRefundedEvent event) {
        log.info("OrderHistory pull OrderRefundedEvent: {}", event);
        orderHistoryService.refundOrder(event.orderId(), event.customerId());
    }

    @EventHandler
    public void on(OrderCompletedEvent event) {
        log.info("OrderHistory pull OrderCompletedEvent: {}", event);
        orderHistoryService.completeOrder(event.orderId());
    }


}
