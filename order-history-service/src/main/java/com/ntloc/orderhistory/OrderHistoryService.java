package com.ntloc.orderhistory;

import com.ntloc.coreapi.customer.event.CustomerCreatedEvent;
import com.ntloc.coreapi.order.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
//    public OrderView getOrderByUserId(String id) {
//        return orderViewRepository.findByCustomerId(id).orElseThrow(() ->

//                new ResourceNotFoundException("Customer was not found"));
//    }

    public void addCustomer(CustomerCreatedEvent event) {
        OrderSummaryView orderSummaryView = OrderSummaryView.builder()
                .customer(new CustomerView(event.customerId(),
                        event.name(),
                        event.email(),
                        event.gender()))
                .build();
        orderSummaryViewRepository.insert(orderSummaryView);
    }

    public void addOrder(OrderCreatedEvent event) {
        OrderSummaryView orderSummaryView = orderSummaryViewRepository.findByCustomer_CustomerId(event.orderDetails().customerId()).orElseThrow(() ->
                new IllegalStateException(String.format("Order of customerId %s was not found", event.orderDetails().customerId())));
        orderSummaryView.getOrders().add(new OrderView(event.orderId(),
                event.orderDetails().lineItems(),
                BigDecimal.TEN));
//        OrderSummaryView orderSummaryView = OrderSummaryView.builder()
//                .customer(new CustomerView(event.orderDetails().customerId(),
//                        event.orderDetails().customerName())
//                )
//                .orders(Arrays.asList(new OrderView(event.orderId(),
//                        event.orderDetails().lineItems(),
//                        BigDecimal.TEN))
//                )
//                .build();
        OrderSummaryView save = orderSummaryViewRepository.save(orderSummaryView);
        log.info("OrderSummary inserted: {}", save);

    }

    private void updateOrderState(String orderId, OrderState state) {
        log.info("Start update order state for oderId: {} - state: {}", orderId, state);
    }

    public void cancelOrder(String orderId) {
        updateOrderState(orderId, OrderState.CANCELLED);
    }

    public void refundOrder(String orderId) {
        updateOrderState(orderId, OrderState.REFUNDED);
    }

    public void completeOrder(String orderId) {
        updateOrderState(orderId, OrderState.COMPLETED);
    }


//    public void cancelOrder(String orderId) {
//        OrderSummaryView orderSummaryView = orderSummaryViewRepository.findByOrdersOrderId(orderId).orElseThrow(() ->
//                new ResourceNotFoundException(ORDER_WAS_NOT_FOUND));
//        orderSummaryView.getOrders().cancel();
//        orderSummaryViewRepository.save(orderSummaryView);
//    }
//
//    public void refundOrder(String orderId) {
//        OrderView orderView = orderSummaryViewRepository.findByOrderId(orderId).orElseThrow(() ->
//                new ResourceNotFoundException(ORDER_WAS_NOT_FOUND));
//        orderView.refund();
//        orderSummaryViewRepository.insert(orderView);
//    }

}
