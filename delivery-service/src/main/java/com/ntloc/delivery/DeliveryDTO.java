package com.ntloc.delivery;

public record DeliveryDTO(Long id,
                          Long orderId,
                          String address,
                          DeliveryState state) {
}
