package com.ntloc.payment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


public record PaymentDTO(Long id,
                         Long orderId,
                         PaymentState state,
                         LocalDateTime createAt) {


}
