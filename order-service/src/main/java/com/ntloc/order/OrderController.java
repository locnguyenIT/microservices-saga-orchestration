package com.ntloc.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping(path = "/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping(path = "/{id}")
    public OrderDTO getOrder(@PathVariable("id") Long id) {
        log.info("Get OrderId: {}", id);
        return orderService.getOrder(id);
    }

    @PostMapping
    public OrderDTO order(@RequestBody OrderRequest orderRequest) {
        log.info("Customer order: {}", orderRequest);
        return orderService.order(orderRequest);
    }

}
