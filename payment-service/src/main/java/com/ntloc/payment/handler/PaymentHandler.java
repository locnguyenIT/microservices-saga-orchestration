package com.ntloc.payment.handler;

import com.ntloc.coreapi.payment.event.PaymentFailedEvent;
import com.ntloc.coreapi.payment.event.PaymentSucceededEvent;
import com.ntloc.payment.Payment;
import com.ntloc.payment.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentHandler {

    private final PaymentRepository paymentRepository;

    public PaymentHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @EventHandler
    public void on(PaymentSucceededEvent event) {
        Payment order = new Payment(event.paymentId(), event.orderId());
        paymentRepository.save(order);
    }

    @EventHandler
    public void on(PaymentFailedEvent event) {
        Payment order = new Payment(event.paymentId(), event.orderId(), event.failedReason());
        paymentRepository.save(order);
    }


}
