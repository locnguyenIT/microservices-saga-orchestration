package com.ntloc.order;

public record OrderDTO(String id,
                       OrderDetails orderDetails,
                       OrderState state,
                       FailedReason failedReason) {

}
