package com.ntloc.customer;

import java.math.BigDecimal;

public record CustomerDTO(Long id,
                          String name,
                          String email,
                          Gender gender,
                          BigDecimal money) {
}
