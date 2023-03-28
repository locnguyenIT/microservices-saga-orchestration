package com.ntloc.notification;

import java.time.LocalDateTime;

public record NotificationDTO(Long id,
                              Long toCustomerId,
                              String toCustomerName,
                              String toCustomerEmail,
                              String sender,
                              String message,
                              LocalDateTime sentAt) {

}
