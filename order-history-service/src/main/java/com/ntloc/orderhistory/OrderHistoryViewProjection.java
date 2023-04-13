package com.ntloc.orderhistory;

import com.ntloc.coreapi.order.event.OrderCancelledEvent;
import com.ntloc.coreapi.order.event.OrderCreatedEvent;
import com.ntloc.coreapi.order.event.OrderRefundedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderHistoryViewProjection {

    private final OrderHistoryViewService orderHistoryViewService;

    public OrderHistoryViewProjection(OrderHistoryViewService orderHistoryViewService) {
        this.orderHistoryViewService = orderHistoryViewService;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        log.info("OrderHistory pull OrderCreatedEvent: {}", event);
        orderHistoryViewService.createOrder(event);

    }

    @EventHandler
    public void on(OrderCancelledEvent event) {
        log.info("OrderHistory pull OrderCancelledEvent: {}", event);
        orderHistoryViewService.cancelOrder(event.orderId());

    }

    @EventHandler
    public void on(OrderRefundedEvent event) {
        log.info("OrderHistory pull OrderRefundedEvent: {}", event);
        orderHistoryViewService.refundOrder(event.orderId());
    }


}
