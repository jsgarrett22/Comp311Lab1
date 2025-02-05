package com.garrett;

import java.util.Stack;

/**
 * The class that simulates the push command that places either a constant or the value
 * of a variable on top of the stack.
 * @author Franklin U. Comp 311
 */
public class PushOperation implements Operation {
	
    private int value;
    private String symbol;
    
    public PushOperation(String symbol) {
    	this.value = -1;
    	this.symbol = symbol;
    }

    public PushOperation(int value) {
        this.value = value;
        this.symbol = "";
        System.out.println("New PushOperation created with value: " + value);
    }

    @Override
    public int execute(int programCounter, Stack<Integer> stack, SymbolTable symbolTable) {
    	if (symbol.isEmpty()) {
    		stack.push(value);
            System.out.println("Pushing value '" + value + "' on stack.");
    		return programCounter + 1;
    	} else {
    		stack.push(symbolTable.getValue(symbol));
    		System.out.println("Pushing value '" + value + "' on stack.");
    		return programCounter + 1;
    	}
    }
    
    /**
     * Returns the operations value.
     * @return the value of the operation.
     */
    public int getValue() {
    	return this.value;
    }

}
