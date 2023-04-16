package com.ntloc.customer.axon;

import com.ntloc.coreapi.customer.event.CustomerCreatedEvent;
import com.ntloc.customer.Customer;
import com.ntloc.customer.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerProjection {

    private final CustomerRepository customerRepository;

    public CustomerProjection(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @EventHandler
    public void on(CustomerCreatedEvent event) {
        log.info("CustomerProjection pull OrderCreatedEvent: {}", event);
        Customer customer = new Customer(event.customerId(), event.name(), event.email(), event.gender());
        customerRepository.save(customer);

    }
}
