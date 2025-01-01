package org.clyze.JInfoFlowBench.application.ctx_sensitivity_only;

import java.io.*;
import edu.ucr.cs.riple.taint.ucrtainting.qual.RUntainted;

/**
 * Created by neville on 07/11/2016.
 */
public class InstanceAndStaticMethods {


    static class Box {
        @RUntainted String o;

        Box(@RUntainted String o) {
            this.o = o;
        }

        @RUntainted String get() {
            return get1();
        }

        @RUntainted String get1() {
            return get2();
        }

        @RUntainted String get2() {
            return get3();
        }

        @RUntainted String get3() {
            return o;
        }

    }

    public static @RUntainted String boxGetter(Box box) {
        return box.get();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("source"));
        PrintWriter writer = new PrintWriter(new FileWriter("sink"));

        Box box = new Box(reader.readLine()); // source
        Box box2 = new Box("Hello world!");

        writer.println(boxGetter(box)); // bad
        writer.println(boxGetter(box2)); // ok

    }
}
