package com.ntloc.ordeview;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/order-view")
public class OrderViewController {

    private final OrderViewService orderViewService;

    @GetMapping(path = "/{id}")
    public Order getOrderByUserId(@PathVariable("id") String id) {
        return orderViewService.getOrderByUserId(id);
    }

}
