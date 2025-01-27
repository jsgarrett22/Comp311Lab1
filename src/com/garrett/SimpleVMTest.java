package com.garrett;

import java.io.StringReader;
import java.util.Scanner;

import junit.framework.TestCase;

public class SimpleVMTest extends TestCase {
    public void testPushPop() throws Exception {
        Scanner reader = new Scanner(new StringReader(
                "push 5\n"
                + "pop x\n"
                ));

        SimpleVM vm = new SimpleVM(reader);
        vm.run();
        assertEquals(5, vm.getValue("x"));
    }

    public void testWriteup() throws Exception {
        Scanner reader = new Scanner(new StringReader(
                "push 5\n"
                + "push 7\n"
                + "add\n"
                + "pop x\n"
                + "push x\n"
                + "push 3\n"
                + "add\n"
                + "pop y\n"
                ));

        SimpleVM vm = new SimpleVM(reader);
        vm.run();
        assertEquals(12, vm.getValue("x"));
        assertEquals(15, vm.getValue("y"));
    }
}
