package com.ntloc.coreapi.customer.model;

public record CreateCustomerRequest(String name,
                                    String email,
                                    Gender gender) {
}
