package com.ntloc.coreapi.customer.command;

import com.ntloc.coreapi.customer.model.Gender;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CreateCustomerCommand(@TargetAggregateIdentifier String customerId,
                                    String name,
                                    String email,
                                    Gender gender,
                                    Long money) {
}
