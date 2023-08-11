package com.ntloc.customer.axon;

import com.ntloc.coreapi.customer.event.CustomerCreatedEvent;
import com.ntloc.coreapi.customer.query.FetchCustomerMoneyQuery;
import com.ntloc.customer.Customer;
import com.ntloc.customer.CustomerRepository;
import com.ntloc.customer.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import static com.ntloc.customer.CustomerConstant.MessagesConstant.CUSTOMER_WAS_NOT_FOUND;

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
        Customer customer = new Customer(event.customerId(), event.name(), event.email(), event.gender(), event.money());
        customerRepository.save(customer);

    }

    @QueryHandler
    public Long getMoney(FetchCustomerMoneyQuery fetchCustomerMoneyQuery) {
        log.info("Handle FetchCustomerMoneyQuery: {}", fetchCustomerMoneyQuery.customerId());
        return customerRepository.findById(fetchCustomerMoneyQuery.customerId())
                .map(Customer::getMoney)
                .orElseThrow(() -> new ResourceNotFoundException(CUSTOMER_WAS_NOT_FOUND));
    }
}
