package com.ntloc.coreapi.customer.event;

import com.ntloc.coreapi.customer.model.Gender;

public record CustomerCreatedEvent(String customerId,
                                   String name,
                                   String email,
                                   Gender gender,
                                   Long money) {
}
