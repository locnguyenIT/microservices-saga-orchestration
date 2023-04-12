package com.ntloc.order;

import java.math.BigDecimal;

public record CreateOrderRequest(Long customerId,
                                 Long productId,
                                 Integer quantity,
                                 BigDecimal totalMoney) {
}
