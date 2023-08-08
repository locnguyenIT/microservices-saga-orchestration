package com.ntloc.payment;

import com.ntloc.coreapi.messages.FailedReason;
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
        this.state = SUCCEEDED;
        this.createAt = LocalDateTime.now();
    }

    public Payment(String id, String orderId, FailedReason failedReason) {
        this.id = id;
        this.orderId = orderId;
        this.state = FAILED;
        this.failedReason = failedReason;
        this.createAt = LocalDateTime.now();
    }

    public void succeeded() {
        this.state = SUCCEEDED;
    }

    public void failed(FailedReason failedReason) {
        this.state = FAILED;
        this.failedReason = failedReason;
    }

    public void cancel() {
        this.state = CANCELED;
    }

}
