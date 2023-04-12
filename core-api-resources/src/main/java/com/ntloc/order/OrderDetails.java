package com.ntloc.order;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
