package com.ntloc.delivery;

import java.time.LocalDateTime;

public record DeliveryDTO(Long id,
                          Long orderId,
                          String address,
                          DeliveryState state) {
}
