package org.clyze.JInfoFlowBench.application.ctx_sensitivity_only;

import java.io.*;
import edu.ucr.cs.riple.taint.ucrtainting.qual.RPolyTainted;

/**
 * Created by neville on 07/11/2016.
 */
public class StaticMethods {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("source"));
        PrintWriter writer = new PrintWriter(new FileWriter("sink"));
        writer.println(id(reader.readLine())); // source and sink
        writer.println(id("Hello")); // ok
    }

    public static @RPolyTainted String id(@RPolyTainted String o) {
        return o;
    }
}
