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
    }

    @Override
    public int execute(int programCounter, Stack<Integer> stack, SymbolTable symbolTable) {
        stack.push(value);
        return programCounter + 1;
    }

}
