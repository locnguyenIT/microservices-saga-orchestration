package com.ntloc.payment.aggregate;

import com.ntloc.coreapi.payment.command.PaymentOrderCommand;
import com.ntloc.coreapi.payment.event.PaymentFailedEvent;
import com.ntloc.coreapi.payment.event.PaymentSucceededEvent;
import com.ntloc.payment.PaymentState;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import static com.ntloc.payment.PaymentState.FAILED;
import static com.ntloc.payment.PaymentState.SUCCEEDED;

@Aggregate
@ToString
@Slf4j
public class PaymentAggregate {

    @AggregateIdentifier
    private String paymentId;
    private String orderId;
    private PaymentState state;

    public PaymentAggregate() {
    }

    @CommandHandler
    public PaymentAggregate(PaymentOrderCommand command) {
        log.info("Receive ProcessPaymentCommand for orderId : {}", command.orderId());
        //TODO: validate the ProcessPaymentCommand

        AggregateLifecycle.apply(new PaymentSucceededEvent(command.paymentId(), command.orderId()));
    }

    @EventSourcingHandler
    public void on(PaymentSucceededEvent event) {
        log.info("Receive PaymentSucceededEvent of orderId: {} ", event.orderId());
        this.paymentId = event.paymentId();
        this.orderId = event.orderId();
        this.state = SUCCEEDED;
        log.info("Updated PaymentAggregate after PaymentSucceededEvent: " + this);
    }

    @EventSourcingHandler
    public void on(PaymentFailedEvent event) {
        log.info("Receive PaymentFailedEvent of orderId: {} ", event.paymentId());
        this.paymentId = event.paymentId();
        this.orderId = event.orderId();
        this.state = FAILED;
        log.info("Updated PaymentAggregate after PaymentFailedEvent: " + this);
    }
}
