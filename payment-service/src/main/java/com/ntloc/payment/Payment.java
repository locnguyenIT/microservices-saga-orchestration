package com.ntloc.payment;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.ntloc.payment.PaymentState.COMPLETED;
import static com.ntloc.payment.PaymentState.FAILED;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    @Enumerated(value = EnumType.STRING)
    private PaymentState state;
    @Enumerated(value = EnumType.STRING)
    private FailedReason failedReason;
    private LocalDateTime createAt;

    public Payment(Long orderId) {
        this.orderId = orderId;
        this.createAt = LocalDateTime.now();
        this.state = COMPLETED;
    }

    public void completed() {
        this.state = COMPLETED;
    }

    public void failed(FailedReason failedReason) {
        this.state = FAILED;
        this.failedReason = failedReason;
    }

}
