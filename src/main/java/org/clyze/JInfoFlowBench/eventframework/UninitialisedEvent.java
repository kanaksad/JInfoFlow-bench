package org.clyze.JInfoFlowBench.eventframework;

import java.lang.reflect.InvocationTargetException;
import edu.ucr.cs.riple.taint.ucrtainting.qual.RUntainted;

/**
 * Created by neville on 01/11/2016.
 */
public class UninitialisedEvent {
    private final @RUntainted Event event;
    private final EventDriver driver;

    public UninitialisedEvent(EventDriver driver, @RUntainted Event event) {
        this.driver = driver;
        this.event = event;
    }

    public UninitialisedEvent apply(String name, @RUntainted Object value) {
        try {
            event.getClass().getMethod(name, value.getClass()).invoke(event, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void raiseEvent() {
        driver.raiseEvent(event);
    }
}
