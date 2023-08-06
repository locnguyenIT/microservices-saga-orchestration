package com.ntloc.coreapi.order.model;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record OrderDetails(String customerId,
                           List<OrderLineItem> lineItems,
                           Long totalMoney) {


}
