package com.ntloc.orderhistory;

import com.ntloc.order.FailedReason;
import com.ntloc.order.OrderDetails;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.ntloc.orderhistory.OrderState.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "orderview")
public class OrderView {

    @Id
    private String consumerId;
    private String orderId;

    private OrderDetails orderDetails;
    private OrderState state;
    private FailedReason failedReason;
    private String customerId;
    private String customerName;

    public OrderView(String consumerId, String orderId, OrderDetails orderDetails, String customerId, String customerName) {
        this.consumerId = consumerId;
        this.orderId = orderId;
        this.orderDetails = orderDetails;
        this.state = CREATED;
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public void cancel() {
        this.state = CANCELLED;
    }

    public void refund() {
        this.state = REFUNDED;
    }
}
