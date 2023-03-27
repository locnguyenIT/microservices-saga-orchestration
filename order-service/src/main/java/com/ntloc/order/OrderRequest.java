package com.ntloc.order;

import java.math.BigDecimal;

public record OrderRequest(Long customerId,
                           Long productId,
                           Integer quantity,
                           BigDecimal totalMoney) {
}
