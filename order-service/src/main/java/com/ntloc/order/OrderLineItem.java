package com.ntloc.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineItem {

    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
}
