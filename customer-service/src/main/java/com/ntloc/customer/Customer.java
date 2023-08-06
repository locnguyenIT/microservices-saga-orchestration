package com.ntloc.customer;

import com.ntloc.coreapi.customer.model.Gender;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    private String id;
    private String name;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private Long money;

}
