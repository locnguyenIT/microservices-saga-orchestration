package com.ntloc.coreapi.order.event;

import com.ntloc.coreapi.messages.FailedReason;

public record OrderFailedEvent(String orderId, FailedReason failedReason) {
}
