package com.garrett;

import java.util.Stack;

/**
 * Operation to push a value onto the stack.
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
