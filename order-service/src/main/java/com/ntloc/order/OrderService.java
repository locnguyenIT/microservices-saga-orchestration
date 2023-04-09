package com.ntloc.order;

import com.ntloc.order.exception.ResourceNotFoundException;
import com.ntloc.order.saga.command.CreateOrderCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.ntloc.order.OrderConstant.MessagesConstant.ORDER_WAS_NOT_FOUND;

@AllArgsConstructor
@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final CommandGateway commandGateway;

    public List<OrderDTO> getAllOrders() {
        List<Order> listOrder = orderRepository.findAll();
        return orderMapper.toListOrderDTO(listOrder);
    }

    public OrderDTO getOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(ORDER_WAS_NOT_FOUND));
        return orderMapper.toOrderDTO(order);
    }

    @Transactional
    public String order(OrderRequest orderRequest) {
//        Order order = orderRepository.save(new Order(OrderDetails.builder()
//                .customerId(orderRequest.customerId())
//                .productId(orderRequest.productId())
//                .quantity(orderRequest.quantity())
//                .totalMoney(orderRequest.totalMoney())
//                .build())
//        );


        CreateOrderCommand createOrderCommand = new CreateOrderCommand(UUID.randomUUID().toString(),
                OrderDetails.builder()
                .customerId(orderRequest.customerId())
                .productId(orderRequest.productId())
                .quantity(orderRequest.quantity())
                .totalMoney(orderRequest.totalMoney())
                        .build(), OrderState.CREATED);
//        CreateOrderCommand createOrderCommand = new CreateOrderCommand(order.getId(),order.getOrderDetails(),order.getState());
//        if (orderRequest.getQuantity() < 5) {
//            order.approve();
//            //TODO: Update quantity of product
//        } else {
//            order.reject(INSUFFICIENT_QUANTITY);
//        }
        String o = commandGateway.sendAndWait(createOrderCommand);
        log.info("command value "+o);
        return createOrderCommand.getOrderId();

    }
}
