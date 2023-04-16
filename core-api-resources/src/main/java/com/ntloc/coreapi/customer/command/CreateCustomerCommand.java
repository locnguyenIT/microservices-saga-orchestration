package com.ntloc.coreapi.customer.command;

import com.ntloc.coreapi.customer.model.Gender;

public record CreateCustomerCommand(String customerId,
                                    String name,
                                    String email,
                                    Gender gender) {
}
