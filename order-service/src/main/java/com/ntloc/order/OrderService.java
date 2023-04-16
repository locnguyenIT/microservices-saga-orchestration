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
        List<Order> listOrder = orderRepository.findAll();
        return orderMapper.toListOrderDTO(listOrder);
    }

    public OrderDTO getOrder(String id) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(ORDER_WAS_NOT_FOUND));
        return orderMapper.toOrderDTO(order);
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
        CancelOrderCommand cancelOrderCommand = new CancelOrderCommand(id);
        return commandGateway.send(cancelOrderCommand);
    }

    public CompletableFuture<String> refundOrder(String id) {
        RefundOrderCommand cancelOrderCommand = new RefundOrderCommand(id);
        return commandGateway.send(cancelOrderCommand);
    }
}
