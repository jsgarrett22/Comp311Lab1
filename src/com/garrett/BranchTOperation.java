package com.garrett;

import java.util.Stack;

public class BranchTOperation implements Operation {
	
	private int value;
	
	public BranchTOperation(int value) {
		this.value = value;
		System.out.println("New BranchTOperation created.");
	}

	@Override
	public int execute(int programCounter, Stack<Integer> stack, SymbolTable symbolTable) {
		// grab the top of the stack
		int result = stack.pop();
		// if it is 1 (true), then return the index of the associated branch
		if (result == 1) {
			return this.value;
		} else {
			return programCounter + 1;
		}
	}

}
