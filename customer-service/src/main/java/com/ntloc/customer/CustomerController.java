package com.ntloc.customer;

import com.ntloc.coreapi.customer.model.CreateCustomerRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @GetMapping(path = "/{id}")
    public CustomerDTO getCustomer(@PathVariable("id") String id) {
        return customerService.getCustomer(id);
    }

    @PostMapping
    public CompletableFuture<String> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
        return customerService.createCustomer(createCustomerRequest);
    }

}
