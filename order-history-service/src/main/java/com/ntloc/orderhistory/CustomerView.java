package com.ntloc.orderhistory;

import com.ntloc.coreapi.customer.model.Gender;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CustomerView {

    private String customerId;
    private String name;

    private String email;
    private Gender gender;
}
