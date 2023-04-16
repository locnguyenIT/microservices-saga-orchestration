package com.ntloc.customer.axon;

import com.ntloc.coreapi.customer.command.CreateCustomerCommand;
import com.ntloc.coreapi.customer.event.CustomerCreatedEvent;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@ToString
@Aggregate
public class CustomerAggregate {

    @AggregateIdentifier
    private String customerId;

    public CustomerAggregate() {
    }

    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand command) {
        log.info("Receive CreateCustomerCommand: {}", command);
        CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent(command.customerId(),
                command.name(),
                command.email(),
                command.gender());
        AggregateLifecycle.apply(customerCreatedEvent);
    }

    @EventSourcingHandler
    public void on(CustomerCreatedEvent event) {
        log.info("Pull CustomerCreatedEvent: {} " + event);
        this.customerId = event.customerId();
        log.info("Updated CustomerAggregate after CustomerCreatedEvent: " + this);
    }
}
