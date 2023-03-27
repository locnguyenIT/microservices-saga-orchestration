package com.ntloc.order;

import java.time.LocalDateTime;

public record OrderDTO(Long id,
                       Long customerId,
                       Long productId,
                       Integer quantity,
                       LocalDateTime createAt,
                       OrderState state,
                       RejectionReason rejectionReason) {

}
