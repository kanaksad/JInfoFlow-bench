package org.clyze.JInfoFlowBench.eventframework;

import java.io.Serializable;
import edu.ucr.cs.riple.taint.ucrtainting.qual.RUntainted;

/**
 * Created by neville on 01/11/2016.
 */
public abstract class Event {
    public abstract String getEventName();

    private @RUntainted String metaData;

    public abstract String getAggregateId();

    public void setMetaData(@RUntainted String metaData) {
        this.metaData = metaData;
    }

    public @RUntainted String getMetaData() {
        return metaData;
    }

    public abstract Event getCopy();


}
