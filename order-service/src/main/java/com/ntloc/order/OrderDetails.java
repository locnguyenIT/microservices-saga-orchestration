package com.ntloc.order;

import lombok.*;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class OrderDetails {

    private Long customerId;
    private Long productId;
    private Integer quantity;
    private BigDecimal totalMoney;
    @Builder.Default
    private LocalDateTime createAt = LocalDateTime.now();

}
