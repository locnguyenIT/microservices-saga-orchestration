package com.ntloc.order.saga.event.handler;

import com.ntloc.order.Order;
import com.ntloc.order.OrderRepository;
import com.ntloc.order.saga.event.OrderCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

//@Component
public class OrderEventHandler {

//    private final OrderRepository orderRepository;
//
//    public OrderEventHandler(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }
//
//    @EventHandler
//    public void on(OrderCreatedEvent orderCreatedEvent) {
//        Order order = new Order(orderCreatedEvent.getOrderDetails());
//        orderRepository.save(order);
//    }
}
