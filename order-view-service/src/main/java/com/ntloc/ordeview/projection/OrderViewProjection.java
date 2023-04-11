package com.ntloc.ordeview.projection;

import com.ntloc.ordeview.Order;
import com.ntloc.ordeview.OrderViewRepository;
import com.ntloc.ordeview.exception.ResourceNotFoundException;
import com.ntloc.ordeview.query.FindOrderQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class OrderViewProjection {

    private final OrderViewRepository orderViewRepository;

    public OrderViewProjection(OrderViewRepository orderViewRepository) {
        this.orderViewRepository = orderViewRepository;
    }

    @QueryHandler
    public Order findCustomerOrder(FindOrderQuery findOrderQuery) {
        Order order = orderViewRepository.findById(findOrderQuery.getOrderId()).orElseThrow(() ->
                new ResourceNotFoundException("Order was not found"));
        return order;
    }
}
