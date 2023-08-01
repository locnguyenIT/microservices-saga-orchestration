package com.ntloc.order;

import com.ntloc.coreapi.messages.OrderState;
import com.ntloc.coreapi.messages.Reason;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

import static com.ntloc.coreapi.messages.OrderState.*;


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
    private OrderState state;
    @Enumerated(value = EnumType.STRING)
    private Reason reason;

    public Order(String id, String customerId, List<OrderLineItem> lineItems, BigDecimal moneyTotal) {
        this.id = id;
        this.customerId = customerId;
        this.lineItems = lineItems;
        this.moneyTotal = moneyTotal;
        this.state = CREATED;
    }

    public static Order create(String id, String customerId, List<OrderLineItem> lineItems, BigDecimal moneyTotal) {
        return new Order(id, customerId, lineItems, moneyTotal);
    }

    public void paid() {
        this.state = PAID;
    }

    public void delivered() {
        this.state = DELIVERED;
    }

    public void completed() {
        this.state = COMPLETED;
    }

    public void cancel() {
        this.state = CANCELLED;
//        switch (this.state) {
//            case CREATED -> throw new IllegalStateException("Can't be cancel in order created state");
//            case COMPLETED -> this.state = CANCELLED;
//            default -> throw new IllegalStateException("Can't cancel in this state: " + this.state);
//        }

    }

    public void refund() {
        this.state = REFUNDED;
    }

}
