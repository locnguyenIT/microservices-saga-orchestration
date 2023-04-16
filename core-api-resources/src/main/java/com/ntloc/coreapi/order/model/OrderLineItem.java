package com.ntloc.coreapi.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
