package com.ntloc.order;

import com.ntloc.coreapi.order.command.CancelOrderCommand;
import com.ntloc.coreapi.order.command.CreateOrderCommand;
import com.ntloc.coreapi.order.command.RefundOrderCommand;
import com.ntloc.coreapi.order.model.OrderDetails;
import com.ntloc.order.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.ntloc.order.OrderConstant.MessagesConstant.ORDER_WAS_NOT_FOUND;

@AllArgsConstructor
@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CommandGateway commandGateway;

    public List<OrderDTO> getAllOrders() {
        return orderMapper.toListOrderDTO(orderRepository.findAll());
    }

    public OrderDTO getOrder(String id) {
        return orderRepository.findById(id).map(orderMapper::toOrderDTO).orElseThrow(() ->
                new ResourceNotFoundException(ORDER_WAS_NOT_FOUND));
    }

    public CompletableFuture<String> createOrder(CreateOrderRequest createOrderRequest) {
        CreateOrderCommand createOrderCommand = new CreateOrderCommand(UUID.randomUUID().toString(),
                OrderDetails.builder()
                        .customerId(createOrderRequest.customerId())
                        .lineItems(createOrderRequest.lineItems())
                        .totalMoney(createOrderRequest.moneyTotal())
                        .build()
        );
        return commandGateway.send(createOrderCommand);

    }

    public CompletableFuture<String> cancelOrder(String id) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(ORDER_WAS_NOT_FOUND));
        CancelOrderCommand cancelOrderCommand = new CancelOrderCommand(id, order.getCustomerId());
        return commandGateway.send(cancelOrderCommand);
    }

    public CompletableFuture<String> refundOrder(String id) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(ORDER_WAS_NOT_FOUND));
        RefundOrderCommand cancelOrderCommand = new RefundOrderCommand(id, order.getCustomerId());
        return commandGateway.send(cancelOrderCommand);
    }
}
