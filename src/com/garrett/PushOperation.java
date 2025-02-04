package com.garrett;

import java.util.Stack;

/**
 * The class that simulates the push command that places either a constant or the value
 * of a variable on top of the stack.
 * @author Franklin U. Comp 311
 */
public class PushOperation implements Operation {
    private int value;

    public PushOperation(int value) {
        this.value = value;
        System.out.println("New PushOperation created with value: " + value);
    }

    @Override
    public int execute(int programCounter, Stack<Integer> stack, SymbolTable symbolTable) {
        stack.push(value);
        System.out.println("Pushing value '" + value + "' on stack.");
		return programCounter + 1;
    }

}
