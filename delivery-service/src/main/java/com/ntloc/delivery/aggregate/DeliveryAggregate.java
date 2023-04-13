package com.ntloc.delivery.aggregate;

import com.ntloc.coreapi.delivery.command.DeliveryOrderCommand;
import com.ntloc.coreapi.delivery.event.OrderDeliveredEvent;
import com.ntloc.delivery.DeliveryState;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static com.ntloc.delivery.DeliveryState.DELIVERED;

@Slf4j
@ToString
@Aggregate
public class DeliveryAggregate {

    @TargetAggregateIdentifier
    private String deliveryId;
    private DeliveryState state;

    public DeliveryAggregate() {
    }

    @CommandHandler
    public DeliveryAggregate(DeliveryOrderCommand command) {
        log.info("Receive DeliveryOrderCommand for orderId : {}", command.orderId());
        //TODO: Process Delivery
        OrderDeliveredEvent orderDeliveredEvent = new OrderDeliveredEvent(command.deliveryId(),
                command.orderId());
        AggregateLifecycle.apply(orderDeliveredEvent);
        log.info("Pushed DeliveryOrderEvent of orderId : {}", orderDeliveredEvent.orderId());
    }

    @EventSourcingHandler
    public void on(OrderDeliveredEvent event) {
        log.info("Pull OrderDeliveredEvent of orderId: {}: " + event.orderId());
        this.deliveryId = event.deliveryId();
        this.state = DELIVERED;
        log.info("Updated DeliveryAggregate after OrderDeliveredEvent: " + this);
    }
}
