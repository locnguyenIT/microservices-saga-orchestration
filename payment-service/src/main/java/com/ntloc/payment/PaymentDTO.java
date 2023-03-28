package com.ntloc.payment;

import java.time.LocalDateTime;


public record PaymentDTO(Long id,
                         Long orderId,
                         PaymentState state,
                         LocalDateTime createAt) {


}
