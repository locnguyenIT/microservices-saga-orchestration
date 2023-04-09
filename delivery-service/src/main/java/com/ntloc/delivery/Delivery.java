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
    private String address;
    @Enumerated(value = EnumType.STRING)
    private DeliveryState state;

    public Delivery(Long orderId, String address) {
        this.orderId = orderId;
        this.address = address;
        this.state = COMPLETED;
    }
}
