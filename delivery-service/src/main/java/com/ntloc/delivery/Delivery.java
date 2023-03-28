package com.ntloc.delivery;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.ntloc.delivery.DeliveryState.COMPLETED;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    @Enumerated(value = EnumType.STRING)
    private DeliveryState state;
    private LocalDateTime start;
    private LocalDateTime end;

    public Delivery(Long orderId, LocalDateTime start) {
        this.orderId = orderId;
        this.start = start;
        this.end = LocalDateTime.now();
        this.state = COMPLETED;
    }
}
