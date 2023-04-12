package com.ntloc.orderhistory;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/customers")
public class CustomerOrderHistoryController {

    private final OrderHistoryViewService orderHistoryViewService;

    @GetMapping(path = "/{id}")
    public OrderView getOrderByUserId(@PathVariable("id") String id) {
        return orderHistoryViewService.getOrderByUserId(id);
    }

}
