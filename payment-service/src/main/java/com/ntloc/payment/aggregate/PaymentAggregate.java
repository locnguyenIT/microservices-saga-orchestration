package com.ntloc.payment.aggregate;

import com.ntloc.coreapi.payment.command.ProcessPaymentCommand;
import com.ntloc.coreapi.payment.event.PaymentSucceededEvent;
import com.ntloc.payment.PaymentState;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import static com.ntloc.payment.PaymentState.SUCCEEDED;

@Aggregate
@ToString
@Slf4j
public class PaymentAggregate {

    @AggregateIdentifier
    private String paymentId;
    private PaymentState state;

    public PaymentAggregate() {
    }

    @CommandHandler
    public PaymentAggregate(ProcessPaymentCommand command) {
        log.info("Receive ProcessPaymentCommand for orderId : {}", command.orderId());
        //TODO: validate the ProcessPaymentCommand
        PaymentSucceededEvent paymentSucceededEvent = new PaymentSucceededEvent(command.paymentId(),
                command.orderId());
        AggregateLifecycle.apply(paymentSucceededEvent);
        log.info("Pushed PaymentSucceededEvent of orderId : {}", paymentSucceededEvent.orderId());
    }

    @EventSourcingHandler
    public void on(PaymentSucceededEvent event) {
        log.info("Pull PaymentSucceededEvent of paymentId: {}: " + event.paymentId());
        this.paymentId = event.paymentId();
        this.state = SUCCEEDED;
        log.info("Updated OrderAggregate after PaymentValidatedEvent: " + this);
    }
}
