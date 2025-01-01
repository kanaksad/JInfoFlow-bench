package org.clyze.JInfoFlowBench.application.ctx_sensitivity_only;

import java.io.*;
import edu.ucr.cs.riple.taint.ucrtainting.qual.RUntainted;

/**
 * Created by neville on 07/11/2016.
 */
public class InstanceMethods {
    static class Box1 {
        private @RUntainted Object o;
        Box1(@RUntainted Object o) {
            this.o = o;
        }
        @RUntainted Object get() { return o; }
    }

    static class Box2 {
        private Box1 box1;
        Box2(@RUntainted Object o) {
            box1 = new Box1(o);
        }

        @RUntainted Object get() {
            return get1();
        }

        private @RUntainted Object get1() {
            return box1.get();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("source"));
        PrintWriter writer = new PrintWriter(new FileWriter("sink"));
        Box2 box2 = new Box2(reader.readLine()); // source
        String out = (String) box2.get();
        writer.println(out); // sink

    }
}
