package com.ntloc.coreapi.order.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Embeddable
public class OrderDetails {

    private Long customerId;
    private Long productId;
    private Integer quantity;
    private BigDecimal totalMoney;
    @Builder.Default
    private LocalDateTime createAt = LocalDateTime.now();

}
