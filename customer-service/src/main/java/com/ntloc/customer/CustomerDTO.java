package com.ntloc.customer;

import com.ntloc.coreapi.customer.model.Gender;

public record CustomerDTO(String id,
                          String name,
                          String email,
                          Gender gender) {
}
