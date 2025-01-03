package org.clyze.JInfoFlowBench.application.event_based;

import org.clyze.JInfoFlowBench.eventframework.EventDriver;
import org.clyze.JInfoFlowBench.eventframework.EventHandler;
import org.clyze.JInfoFlowBench.events.NewEntityEvent;
import org.clyze.JInfoFlowBench.events.NewTransactionEvent;

import java.io.*;
import edu.ucr.cs.riple.taint.ucrtainting.qual.RUntainted;

/**
 * Created by neville on 01/11/2016.
 */
public class Events1 implements EventHandler{

    PrintWriter writer = null;

    public Events1() throws IOException{

        writer = new PrintWriter(new FileWriter("sink"));
    }

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("source"));
        Events1 app = new Events1();
        EventDriver driver = new EventDriver();
        driver.registerAsEventHandler(app);
        NewEntityEvent event = (NewEntityEvent) driver.newEvent("NewEntity");
        NewTransactionEvent event2 = (NewTransactionEvent) driver.newEvent("NewTransaction");
        event.setId(reader.readLine());
        event2.setDestinationEntityId(reader.readLine());
        driver.raiseEvent(event);
        driver.raiseEvent(event2);
    }

    public void handleNewEntity(NewEntityEvent e) throws IOException {
        writer.println(e.getId()); // not ok
        writer.flush();
    }

    public void handleNewTransaction(NewTransactionEvent e) throws IOException {
        writer.println("foo"); // ok
    }

}
