package com.ntloc.order;

import com.ntloc.coreapi.order.model.OrderDetails;

public record OrderDTO(String id,
                       OrderDetails orderDetails,
                       OrderState state,
                       FailedReason failedReason) {

}
