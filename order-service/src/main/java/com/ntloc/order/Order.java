package com.ntloc.order;

import com.ntloc.coreapi.order.model.OrderState;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

import static com.ntloc.coreapi.order.model.OrderState.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String id;
    private String customerId;
    @ElementCollection
    @CollectionTable(name = "order_line_items")
    private List<OrderLineItem> lineItems;

    private BigDecimal moneyTotal;
    @Enumerated(value = EnumType.STRING)
    @Builder.Default
    private OrderState state = CREATED;
    @Enumerated(value = EnumType.STRING)
    private FailedReason failedReason;

    public Order(String id, String customerId, List<OrderLineItem> lineItems, BigDecimal moneyTotal) {
        this.id = id;
        this.customerId = customerId;
        this.lineItems = lineItems;
        this.moneyTotal = moneyTotal;
        this.state = CREATED;
    }

    public void cancel() {
        this.state = CANCELLED;
    }

    public void refund() {
        this.state = REFUNDED;
    }

    public void completed() {
        this.state = COMPLETED;
    }

    public void failed(FailedReason failedReason) {
        this.state = FAILED;
        this.failedReason = failedReason;
    }

}
