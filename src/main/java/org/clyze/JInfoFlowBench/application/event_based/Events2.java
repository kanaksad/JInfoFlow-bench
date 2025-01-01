package org.clyze.JInfoFlowBench.application.event_based;

import org.clyze.JInfoFlowBench.eventframework.Event;
import org.clyze.JInfoFlowBench.eventframework.EventDriver;
import org.clyze.JInfoFlowBench.eventframework.EventHandler;
import org.clyze.JInfoFlowBench.events.NewEntityEvent;
import org.clyze.JInfoFlowBench.events.NewTransactionEvent;

import java.io.*;
import edu.ucr.cs.riple.taint.ucrtainting.qual.RUntainted;

/**
 * Created by neville on 01/11/2016.
 */
public class Events2 implements EventHandler{

    private final @RUntainted String tainted;
    PrintWriter writer = null;
    EventDriver driver = null;

    public Events2(EventDriver driver, @RUntainted String tainted) throws IOException{
        this.driver = driver;
        this.tainted = tainted;
        writer = new PrintWriter(new FileWriter("sink"));
        driver.registerAsEventHandler(this);

    }

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("source"));

        EventDriver driver = new EventDriver();
        Events2 app = new Events2(driver, reader.readLine());
        Event event = driver.newEvent("NewEntity");
        driver.raiseEvent(event);
    }

    public void handleNewEntity(NewEntityEvent e) throws IOException {
        Event e1 = driver.newEvent("NewTransaction");
        e1.setMetaData(tainted);
        driver.raiseEvent(e1);
    }

    public void handleNewTransaction(NewTransactionEvent e) throws IOException {
        writer.println(e.getMetaData()); // bad
    }

}
