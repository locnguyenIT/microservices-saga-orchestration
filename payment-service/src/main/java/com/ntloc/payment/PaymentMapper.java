package com.ntloc.payment;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment toPayment(PaymentDTO dto);

    List<Payment> toListPayment(List<PaymentDTO> listPaymentDTO);

    PaymentDTO toPaymentDTO(Payment entity);

    List<PaymentDTO> toListPaymentDTO(List<Payment> listPayment);
}
