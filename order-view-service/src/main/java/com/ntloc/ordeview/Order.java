package com.ntloc.ordeview;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "order")
public class Order {

    @Id
    private String consumerId;
    private String orderId;
    private String customerId;
    private String customerName;
}
