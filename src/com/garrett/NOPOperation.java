package com.garrett;

import java.util.Stack;

/**
 * NOP operation does nothing but increment the program counter by 1. Sometimes
 * useful as the target of a branch.
 */
public class NOPOperation implements Operation {
	
	public NOPOperation() {
		System.out.println("New NOPOperation created.");
	}

	@Override
	public int execute(int programCounter, Stack<Integer> stack, SymbolTable symbolTable) {
		return programCounter + 1;
	}

}
