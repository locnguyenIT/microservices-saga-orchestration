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
    private RejectionReason rejectionReason;

    public Order(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
        this.state = CREATED;
    }

    public void approve() {
        this.state = APPROVED;
    }

    public void reject(RejectionReason rejectionReason) {
        this.state = REJECTED;
        this.rejectionReason = rejectionReason;
    }

}
