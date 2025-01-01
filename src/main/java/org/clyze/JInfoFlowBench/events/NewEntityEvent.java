package org.clyze.JInfoFlowBench.events;

import org.clyze.JInfoFlowBench.eventframework.Event;

import java.math.BigDecimal;
import edu.ucr.cs.riple.taint.ucrtainting.qual.RUntainted;

/**
 * Created by neville on 01/11/2016.
 */
public class NewEntityEvent extends Event {

    private @RUntainted String id;

    private String name;

    private BigDecimal balance;

    public @RUntainted String getId() {
        return id;
    }

    public void setId(@RUntainted String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String getEventName() {
        return "NewEntity";
    }

    @Override
    public @RUntainted String getAggregateId() {
        return getId();
    }

    public Event getCopy() {
        return null;
    }
}
