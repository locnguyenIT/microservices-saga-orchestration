package com.ntloc.payment.aggregate;

import com.ntloc.coreapi.messages.FailedReason;
import com.ntloc.coreapi.payment.command.PaymentFailedCommand;
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

import static com.ntloc.coreapi.messages.FailedReason.INSUFFICIENT_CREDIT;
import static com.ntloc.payment.PaymentState.*;

@Aggregate
@ToString
@Slf4j
public class PaymentAggregate {

    @AggregateIdentifier
    private String paymentId;
    private PaymentState state;
    private FailedReason failedReason;

    public PaymentAggregate() {
    }

    @CommandHandler
    public PaymentAggregate(PaymentOrderCommand command) {
        log.info("Receive ProcessPaymentCommand for orderId : {}", command.orderId());
        //TODO: validate the ProcessPaymentCommand
        if (command.customerMoney() < command.totalMoney()) {
            AggregateLifecycle.apply(new PaymentFailedEvent(command.paymentId(), command.orderId(), INSUFFICIENT_CREDIT));
        } else {
            AggregateLifecycle.apply(new PaymentSucceededEvent(command.paymentId(), command.orderId()));
        }


    }

    @CommandHandler
    public void handle(PaymentFailedCommand command) {
        log.info("Receive PaymentFailedCommand for orderId : {}", command.orderId());
        //TODO: validate the ProcessPaymentCommand
        AggregateLifecycle.apply(new PaymentFailedEvent(command.paymentId(), command.orderId(), command.failedReason()));
        log.info("Pushed PaymentFailedEvent for orderId : {}", command.orderId());
    }

    @EventSourcingHandler
    public void on(PaymentSucceededEvent event) {
        log.info("Receive PaymentSucceededEvent of orderId: {} ", event.orderId());
        this.paymentId = event.paymentId();
        this.state = SUCCEEDED;
        log.info("Updated PaymentAggregate after PaymentSucceededEvent: " + this);
    }

    @EventSourcingHandler
    public void on(PaymentFailedEvent event) {
        log.info("Receive PaymentFailedEvent of orderId: {} ", event.orderId());
        this.paymentId = event.paymentId();
        this.state = FAILED;
        this.failedReason = event.failedReason();
        log.info("Updated PaymentAggregate after PaymentFailedEvent: " + this);
    }

}
