package org.clyze.JInfoFlowBench.application.ctx_sensitivity_only;

import java.io.*;
import edu.ucr.cs.riple.taint.ucrtainting.qual.RUntainted;

/**
 * Created by neville on 07/11/2016.
 */
public class OverwriteSinks {
    static class CensoredPrintWriter extends PrintWriter {
        public CensoredPrintWriter(PrintWriter out) {
            super(out);
        }

        public void println(String st) {
            // do nothing
        }
    }

    static class UpgradedPrintWriter extends PrintWriter {
        public UpgradedPrintWriter(PrintWriter out) {
            super(out);
        }

        public void println(@RUntainted String st) {
            super.println(">> " + st);
        }
    }

    public static void main(String[] args) throws IOException {
        PrintWriter writer = new CensoredPrintWriter(new PrintWriter(new FileWriter("sink")));
        PrintWriter writer2 = new UpgradedPrintWriter(new PrintWriter(new FileWriter("sink")));

        BufferedReader reader = new BufferedReader(new FileReader("source"));

        writer.println(reader.readLine()); // ok

        writer2.println(reader.readLine()); // bad
    }
}
