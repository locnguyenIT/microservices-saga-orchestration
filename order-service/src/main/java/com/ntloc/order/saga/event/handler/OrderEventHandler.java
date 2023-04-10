package com.ntloc.order.saga.event.handler;

import com.ntloc.order.Order;
import com.ntloc.order.OrderRepository;
import com.ntloc.order.exception.ResourceNotFoundException;
import com.ntloc.order.saga.event.OrderCancelledEvent;
import com.ntloc.order.saga.event.OrderCreatedEvent;
import com.ntloc.order.saga.event.OrderRefundedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import static com.ntloc.order.OrderConstant.MessagesConstant.ORDER_WAS_NOT_FOUND;

@Component
public class OrderEventHandler {

    private final OrderRepository orderRepository;

    public OrderEventHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        Order order = new Order(orderCreatedEvent.getOrderId(), orderCreatedEvent.getOrderDetails());
        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderCancelledEvent orderCancelledEvent) {
        Order order = orderRepository.findById(orderCancelledEvent.getOrderId()).orElseThrow(() ->
                new ResourceNotFoundException(ORDER_WAS_NOT_FOUND));
        order.cancel();
        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderRefundedEvent orderRefundedEvent) {
        Order order = orderRepository.findById(orderRefundedEvent.getOrderId()).orElseThrow(() ->
                new ResourceNotFoundException(ORDER_WAS_NOT_FOUND));
        order.refund();
        orderRepository.save(order);
    }

}
