package org.clyze.JInfoFlowBench.application.event_based;

import org.clyze.JInfoFlowBench.eventframework.Event;
import org.clyze.JInfoFlowBench.eventframework.EventDriver;
import org.clyze.JInfoFlowBench.eventframework.EventHandler;
import org.clyze.JInfoFlowBench.events.NewEntityEvent;
import org.clyze.JInfoFlowBench.events.NewTransactionEvent;
import org.clyze.JInfoFlowBench.events.UnusedEvent;

import java.io.*;
import edu.ucr.cs.riple.taint.ucrtainting.qual.RUntainted;

/**
 * Created by neville on 01/11/2016.
 */
public class Events4 implements EventHandler{

    private final @RUntainted String _tainted;

    private @RUntainted String tainted;

    PrintWriter writer = null;
    EventDriver driver = null;

    public Events4(EventDriver driver, @RUntainted String tainted) throws IOException{
        this.driver = driver;
        this._tainted = tainted;
        writer = new PrintWriter(new FileWriter("sink"));
        driver.registerAsEventHandler(this);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("source"));
        EventDriver driver = new EventDriver();
        Events4 app = new Events4(driver, reader.readLine());
        Event event = driver.newEvent("NewEntity");
        event.setMetaData("NewTransaction");
        driver.raiseEvent(event);
        driver.raiseEvent(event);
    }

    public void handleNewEntity(NewEntityEvent e) throws IOException {
        driver.raiseEvent(driver.newEvent(e.getMetaData()));
        writer.println(tainted); // not ok second time round
    }

    public void handleNewTransaction(NewTransactionEvent e) throws IOException {
        this.tainted = _tainted; // ok
    }

}
