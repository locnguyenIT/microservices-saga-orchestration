package com.ntloc.orderhistory;

import com.ntloc.coreapi.customer.event.CustomerCreatedEvent;
import com.ntloc.coreapi.messages.OrderState;
import com.ntloc.coreapi.order.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ntloc.coreapi.messages.OrderState.*;

@Service
@Slf4j
public class OrderHistoryService {

    private final OrderSummaryViewRepository orderSummaryViewRepository;

    public OrderHistoryService(OrderSummaryViewRepository orderSummaryViewRepository) {
        this.orderSummaryViewRepository = orderSummaryViewRepository;
    }

    public List<OrderSummaryView> getAllOrderSummary() {
        return orderSummaryViewRepository.findAll();
    }

    public void addCustomer(CustomerCreatedEvent event) {
        OrderSummaryView orderSummaryView = OrderSummaryView.builder()
                .customer(new CustomerView(event.customerId(),
                        event.name(),
                        event.email(),
                        event.gender()))
                .orders(new ArrayList<>())
                .build();
        orderSummaryViewRepository.insert(orderSummaryView);
    }

    public void addOrder(OrderCreatedEvent event) {
        OrderSummaryView orderSummaryView = orderSummaryViewRepository.findByCustomer_CustomerId(event.orderDetails().customerId()).orElseThrow(() ->
                new IllegalStateException(String.format("Order of customerId %s was not found", event.orderDetails().customerId())));
        orderSummaryView.getOrders().add(new OrderView(event.orderId(),
                event.orderDetails().lineItems(),
                BigDecimal.TEN));
        OrderSummaryView save = orderSummaryViewRepository.save(orderSummaryView);
        log.info("OrderSummary inserted: {}", save);

    }

    private void updateOrderState(String orderId, Optional<String> customerId, OrderState state) {
        OrderSummaryView orderSummaryView;
        if (customerId.isPresent()) {
            orderSummaryView = orderSummaryViewRepository.findByCustomer_CustomerId(customerId.get()).orElseThrow(() ->
                    new IllegalStateException(String.format("Order of customerId %s was not found", customerId)));
        } else {
            orderSummaryView = orderSummaryViewRepository.findByOrders_OrderId(orderId).orElseThrow(() ->
                    new IllegalStateException(String.format("OrderId %s was not found", orderId)));

        }
        orderSummaryView.getOrderByOrderId(orderId).setState(state);
        orderSummaryViewRepository.save(orderSummaryView);
        log.info("Start update order state for oderId: {} - state: {}", orderId, state);
    }

    public void paidOrder(String orderId) {
        updateOrderState(orderId, Optional.empty(), PAID);
    }

    public void deliveryOrder(String orderId) {
        updateOrderState(orderId, Optional.empty(), DELIVERED);
    }

    public void completeOrder(String orderId) {
        updateOrderState(orderId, Optional.empty(), COMPLETED);
    }

    public void cancelOrder(String orderId, String customerId) {
        updateOrderState(orderId, Optional.of(customerId), CANCELLED);
    }

    public void refundOrder(String orderId, String customerId) {
        updateOrderState(orderId, Optional.of(customerId), REFUNDED);
    }


}
