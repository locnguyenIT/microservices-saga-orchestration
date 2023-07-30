package com.ntloc.orderhistory;

import com.ntloc.coreapi.messages.FailedReason;
import com.ntloc.coreapi.messages.OrderState;
import com.ntloc.coreapi.order.model.OrderLineItem;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

import static com.ntloc.coreapi.messages.OrderState.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderView {

    private String orderId;
    private List<OrderLineItem> lineItems;
    private BigDecimal moneyTotal;
    private OrderState state;
    private FailedReason failedReason;

    public OrderView(String orderId, List<OrderLineItem> lineItems, BigDecimal moneyTotal) {
        this.orderId = orderId;
        this.lineItems = lineItems;
        this.moneyTotal = moneyTotal;
        this.state = CREATED;
    }

}
