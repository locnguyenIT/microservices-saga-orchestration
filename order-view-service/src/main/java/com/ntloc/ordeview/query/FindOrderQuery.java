package com.ntloc.ordeview.query;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FindOrderQuery {
    private String orderId;
}
