package com.ntloc.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    public OrderDTO getOrder(@PathVariable("id") String id) {
        log.info("Get OrderId: {}", id);
        return orderService.getOrder(id);
    }

    @PostMapping
    public CompletableFuture<String> createOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Customer order: {}", orderRequest);
        return orderService.createOrder(orderRequest);
    }

    @PutMapping(path = "/{id}/cancel")
    public CompletableFuture<String> cancelOrder(@PathVariable("id") String id) {
        log.info("Cancel OrderId: {}", id);
        return orderService.cancelOrder(id);
    }

    @PutMapping(path = "/{id}/refund")
    public CompletableFuture<String> refundOrder(@PathVariable("id") String id) {
        log.info("Refund OrderId: {}", id);
        return orderService.refundOrder(id);
    }

}
