package com.ntloc.payment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

import static com.ntloc.payment.PaymentConstant.MessagesConstant.PAYMENT_WAS_NOT_FOUND;

@AllArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public List<PaymentDTO> getAllPayment() {
        return paymentMapper.toListPaymentDTO(paymentRepository.findAll());
    }

    public PaymentDTO getPayment(String id) {
        return paymentMapper.toPaymentDTO(paymentRepository.findById(id).orElseThrow(() ->
                new ResolutionException(PAYMENT_WAS_NOT_FOUND)));
    }

}
