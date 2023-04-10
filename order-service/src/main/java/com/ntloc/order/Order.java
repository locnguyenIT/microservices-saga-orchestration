package com.ntloc.order;

import lombok.*;

import javax.persistence.*;

import static com.ntloc.order.OrderState.*;

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

    @Enumerated
    private OrderDetails orderDetails;

    @Enumerated(value = EnumType.STRING)
    private OrderState state;
    @Enumerated(value = EnumType.STRING)
    private FailedReason failedReason;

    public Order(String orderId, OrderDetails orderDetails) {
        this.id = orderId;
        this.orderDetails = orderDetails;
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
