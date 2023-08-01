package com.ntloc.payment;

import com.ntloc.coreapi.messages.Reason;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.ntloc.payment.PaymentState.FAILED;
import static com.ntloc.payment.PaymentState.SUCCEEDED;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    private String id;
    private String orderId;
    @Enumerated(value = EnumType.STRING)
    private PaymentState state;
    @Enumerated(value = EnumType.STRING)
    private Reason reason;
    private LocalDateTime createAt;

    public Payment(String id, String orderId) {
        this.id = id;
        this.orderId = orderId;
        this.state = SUCCEEDED;
        this.createAt = LocalDateTime.now();
    }

    public Payment(String id, String orderId, Reason reason) {
        this.id = id;
        this.orderId = orderId;
        this.state = FAILED;
        this.reason = reason;
        this.createAt = LocalDateTime.now();
    }

    public void succeeded() {
        this.state = SUCCEEDED;
    }

    public void failed(Reason reason) {
        this.state = FAILED;
        this.reason = reason;
    }

}
