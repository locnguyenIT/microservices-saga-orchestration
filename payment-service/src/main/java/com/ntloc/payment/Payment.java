package com.ntloc.payment;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.ntloc.payment.PaymentState.*;

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
    private FailedReason failedReason;
    private LocalDateTime createAt;

    public Payment(String id, String orderId) {
        this.id = id;
        this.orderId = orderId;
        this.createAt = LocalDateTime.now();
        this.state = SUCCEEDED;
    }

    public void succeeded() {
        this.state = SUCCEEDED;
    }

    public void failed(FailedReason failedReason) {
        this.state = FAILED;
        this.failedReason = failedReason;
    }

}
