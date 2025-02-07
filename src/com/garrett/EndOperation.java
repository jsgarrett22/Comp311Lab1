package com.garrett;

import java.util.Stack;

public class EndOperation implements Operation {
	
	public EndOperation() {	
		System.out.println("New EndOperation created.");
	}

	@Override
	public int execute(int programCounter, Stack<Integer> stack, SymbolTable symbolTable) {
		return programCounter + 1;
	}

}
