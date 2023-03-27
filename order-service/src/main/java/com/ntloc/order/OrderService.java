package com.ntloc.order;

import com.ntloc.order.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.ntloc.order.OrderConstant.MessagesConstant.ORDER_WAS_NOT_FOUND;

@AllArgsConstructor
@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

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
    public OrderDTO order(OrderRequest orderRequest) {
        Order order = orderRepository.save(new Order(OrderDetails.builder()
                .customerId(1L)
                .productId(2L)
                .totalMoney(BigDecimal.valueOf(100_000))
                .createAt(LocalDateTime.now())
                .build())
        );
//        if (orderRequest.getQuantity() < 5) {
//            order.approve();
//            //TODO: Update quantity of product
//        } else {
//            order.reject(INSUFFICIENT_QUANTITY);
//        }
        return orderMapper.toOrderDTO(order);

    }
}
