package com.ntloc.payment.handler;

import com.ntloc.coreapi.order.event.OrderCreatedEvent;
import com.ntloc.coreapi.payment.event.PaymentSucceededEvent;
import com.ntloc.payment.Payment;
import com.ntloc.payment.PaymentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

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
}
