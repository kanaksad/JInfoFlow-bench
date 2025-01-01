package org.clyze.JInfoFlowBench.events;

import org.clyze.JInfoFlowBench.eventframework.Event;

import java.math.BigDecimal;
import edu.ucr.cs.riple.taint.ucrtainting.qual.RUntainted;

/**
 * Created by neville on 01/11/2016.
 */
public class NewTransactionEvent extends Event {

    public static final String EVENT_NAME = "NewTransaction";

    private @RUntainted String originatorEntityId;

    private @RUntainted String destinationEntityId;

    private BigDecimal amount;

    private String reason;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String getEventName() {
        return "NewTransaction";
    }

    @Override
    public @RUntainted String getAggregateId() {
        return originatorEntityId;
    }

    public Event getCopy() {
        return null;
    }

    public String getOriginatorEntityId() {
        return originatorEntityId;
    }

    public void setOriginatorEntityId(@RUntainted String originatorEntityId) {
        this.originatorEntityId = originatorEntityId;
    }

    public @RUntainted String getDestinationEntityId() {
        return destinationEntityId;
    }

    public void setDestinationEntityId(@RUntainted String destinationEntityId) {
        this.destinationEntityId = destinationEntityId;
    }
}
