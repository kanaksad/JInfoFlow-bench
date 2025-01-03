package org.clyze.JInfoFlowBench.eventframework;

import org.clyze.JInfoFlowBench.events.NewEntityEvent;
import org.clyze.JInfoFlowBench.events.NewTransactionEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import edu.ucr.cs.riple.taint.ucrtainting.qual.RUntainted;

/**
 * Created by neville on 01/11/2016.
 */
public class EventDriver {

    private Collection<@RUntainted EventHandler> eventHandlers = new ArrayList<@RUntainted EventHandler>();


    public @RUntainted Event newEvent(@RUntainted String eventName) {
        Event event;
        try {
            event = (Event) Class.forName("org.clyze.JInfoFlowBench.events." + eventName + "Event").newInstance();
            event.setMetaData(metaData);
        } catch (InstantiationException e1) {
            e1.printStackTrace();
            return null;
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
            return null;
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
            return null;
        }
        return event;
    }

    public UninitialisedEvent createEvent(@RUntainted String eventName) {
        return new UninitialisedEvent(this, newEvent(eventName));
    }

    public void raiseEvent(@RUntainted Event event) {
        String eventName = "handle" + event.getEventName();
        for (EventHandler eventHandler : eventHandlers) {
            Boolean hasHandler = false;
            for (Method method: eventHandler.getClass().getMethods()) {
                if (method.getName().equals(eventName)) {
                    hasHandler = true;
                    break;
                }
            }
            // a fancy way to calling the appropriate event handler

            try {
                if (hasHandler) {
                    Method handlerMethod = eventHandler.getClass().getMethod(eventName, event.getClass());
                    handlerMethod.invoke(eventHandler, new Object[] { event });
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerAsEventHandler(@RUntainted EventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    private @RUntainted String metaData;

}
