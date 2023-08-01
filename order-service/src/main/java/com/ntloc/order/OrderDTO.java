package com.ntloc.order;

import com.ntloc.coreapi.messages.OrderState;
import com.ntloc.coreapi.messages.Reason;

import java.math.BigDecimal;
import java.util.List;

public record OrderDTO(String id,
                       String customerId,
                       List<OrderLineItem> lineItems,
                       BigDecimal moneyTotal,
                       OrderState state,
                       Reason reason) {

}
