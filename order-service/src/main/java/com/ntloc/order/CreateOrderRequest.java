package com.ntloc.order;

import com.ntloc.coreapi.order.model.OrderLineItem;

import java.math.BigDecimal;
import java.util.List;

public record CreateOrderRequest(String customerId,
                                 List<OrderLineItem> lineItems,
                                 Long moneyTotal) {
}
