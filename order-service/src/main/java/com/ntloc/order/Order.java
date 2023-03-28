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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private OrderDetails orderDetails;

    @Enumerated(value = EnumType.STRING)
    private OrderState state;
    @Enumerated(value = EnumType.STRING)
    private FailedReason failedReason;

    public Order(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
        this.state = CREATED;
    }

    public void completed() {
        this.state = SUCCEEDED;
    }

    public void failed(FailedReason failedReason) {
        this.state = FAILED;
        this.failedReason = failedReason;
    }

}
