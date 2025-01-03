package org.clyze.JInfoFlowBench.application.basic;

import java.io.*;
import edu.ucr.cs.riple.taint.ucrtainting.qual.RUntainted;

/**
 * Created by neville on 07/11/2016.
 */
public class Arrays {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("source"));
        PrintWriter writer = new PrintWriter(new FileWriter("sink"));
        String input = reader.readLine(); // source

        @RUntainted Object[] stuff = new Object[] { input, "inkling", 23 };


        writer.println((String) stuff[0]); // bad

        writer.println((Integer) stuff[0]); // ok, ClassCastException
    }

}
