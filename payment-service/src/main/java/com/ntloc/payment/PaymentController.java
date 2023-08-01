package com.ntloc.payment;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public List<PaymentDTO> getAllPayment() {
        return paymentService.getAllPayment();
    }

    @GetMapping(path = "/{id}")
    public PaymentDTO getPayment(@PathVariable("id") String id) {
        log.info("PaymentId {}", id);
        return paymentService.getPayment(id);
    }


}
