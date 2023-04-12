package com.ntloc.orderhistory;

import com.ntloc.order.OrderCreatedEvent;
import com.ntloc.orderhistory.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.ntloc.orderhistory.OrderHistoryConstant.ORDER_WAS_NOT_FOUND;

@Service
public class OrderHistoryViewService {

    private final OrderViewRepository orderViewRepository;

    public OrderHistoryViewService(OrderViewRepository orderViewRepository) {
        this.orderViewRepository = orderViewRepository;
    }

    public OrderView getOrderByUserId(String id) {
        return orderViewRepository.findByCustomerId(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer was not found"));
    }

    public void createOrder(OrderCreatedEvent event) {
        orderViewRepository.save(new OrderView(UUID.randomUUID().toString(),
                event.getOrderId(), event.getOrderDetails(),
                "customerId",
                "customerName"));
    }

    public void cancelOrder(String orderId) {
        OrderView orderView = orderViewRepository.findByOrderId(orderId).orElseThrow(() ->
                new ResourceNotFoundException(ORDER_WAS_NOT_FOUND));
        orderView.cancel();
        orderViewRepository.save(orderView);
    }

    public void refundOrder(String orderId) {
        OrderView orderView = orderViewRepository.findByOrderId(orderId).orElseThrow(() ->
                new ResourceNotFoundException(ORDER_WAS_NOT_FOUND));
        orderView.refund();
        orderViewRepository.save(orderView);
    }

}
