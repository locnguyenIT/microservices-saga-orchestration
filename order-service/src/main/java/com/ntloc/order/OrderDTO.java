package com.ntloc.order;

import java.time.LocalDateTime;

public record OrderDTO(Long id,
                       OrderDetails orderDetails,
                       OrderState state,
                       FailedReason failedReason) {

}
