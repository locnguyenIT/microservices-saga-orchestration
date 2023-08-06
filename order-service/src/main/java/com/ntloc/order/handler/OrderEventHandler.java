package com.ntloc.order.handler;

import com.ntloc.coreapi.delivery.event.OrderDeliveredEvent;
import com.ntloc.coreapi.order.command.DeliveredOrderUpdateCommand;
import com.ntloc.coreapi.order.command.PaidOrderUpdateCommand;
import com.ntloc.coreapi.order.event.*;
import com.ntloc.coreapi.payment.event.PaymentSucceededEvent;
import com.ntloc.order.Order;
import com.ntloc.order.OrderLineItem;
import com.ntloc.order.OrderRepository;
import com.ntloc.order.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.ntloc.order.OrderConstant.MessagesConstant.ORDER_WAS_NOT_FOUND;

@Component
@Slf4j
public class OrderEventHandler {

    private final OrderRepository orderRepository;
    private final CommandGateway commandGateway;
    public OrderEventHandler(OrderRepository orderRepository, CommandGateway commandGateway, DomainEventStream domainEventStream) {
        this.orderRepository = orderRepository;
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        log.info("Event handle OrderCreatedEvent {}", event);
        List<OrderLineItem> lineItems = new ArrayList<>();
        BeanUtils.copyProperties(event.orderDetails().lineItems(),
                lineItems);
        Order order = Order.create(event.orderId(), event.orderDetails().customerId(), lineItems, event.orderDetails().totalMoney());
        orderRepository.save(order);
    }

    @EventHandler
    public void on(PaymentSucceededEvent event) {
        log.info("Event handle PaymentSucceededEvent {}", event);
        Order order = orderRepository.findById(event.orderId()).orElseThrow(() ->
                new ResourceNotFoundException(ORDER_WAS_NOT_FOUND));
        order.paid();
        commandGateway.send(new PaidOrderUpdateCommand(order.getId()));
        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderDeliveredEvent event) {
        log.info("Event handle OrderDeliveredEvent {}", event);
        Order order = orderRepository.findById(event.orderId()).orElseThrow(() ->
                new ResourceNotFoundException(ORDER_WAS_NOT_FOUND));
        order.delivered();
        commandGateway.send(new DeliveredOrderUpdateCommand(order.getId()));
        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderCompletedEvent event) {
        log.info("Event handle OrderCompletedEvent {}", event);
        Order order = orderRepository.findById(event.orderId()).orElseThrow(() ->
                new ResourceNotFoundException(ORDER_WAS_NOT_FOUND));
        order.completed();
        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderCancelledEvent event) {
        log.info("Event handle OrderCancelledEvent {}", event);
        Order order = orderRepository.findById(event.orderId()).orElseThrow(() ->
                new ResourceNotFoundException(ORDER_WAS_NOT_FOUND));
        order.cancel();
        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderRefundedEvent event) {
        log.info("Event handle OrderRefundedEvent {}", event);
        Order order = orderRepository.findById(event.orderId()).orElseThrow(() ->
                new ResourceNotFoundException(ORDER_WAS_NOT_FOUND));
        order.refund();
        orderRepository.save(order);
    }

}
